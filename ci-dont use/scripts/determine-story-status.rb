#!/usr/bin/env ruby

require 'English'
require 'json'
require 'uri'
require 'net/http'
require 'openssl'

ENV['http_proxy'] = 'http://thd-svr-proxy-qa.homedepot.com:7070'
ENV['https_proxy'] = 'http://thd-svr-proxy-qa.homedepot.com:7070'
ENV['no_proxy'] = '.homedepot.com'
ENV['NO_PROXY'] = '.homedepot.com'

`git remote add local ../master-code-src/.git`
`git fetch local -q`

# https://git-scm.com/docs/git-log#git-log-ltrevisionrangegt
commit_history = `git log local/master..`

story_nums = commit_history.scan(/\[(?:fix|fixes|fixed|complete|completes|completed|finish|finishes|finished|deliver|delivers|delivered) #(\d+)\]/i).uniq.flatten

accepted_stories = 0

unless story_nums.nil? || story_nums.length < 1

  # approvers = ENV['TRACKER_APPROVERS'].split(",").map(&:strip)

  base_url = "https://www.pivotaltracker.com/services/v5/projects/#{ENV['TRACKER_ID']}"
  base_uri = URI(base_url)

  http = Net::HTTP.new(base_uri.host, base_uri.port)
  http.use_ssl = true
  http.verify_mode = OpenSSL::SSL::VERIFY_NONE

  story_nums.each do |story_num|
    print "Checking Story Number #{story_num}... "

    story_url = base_url+"/stories/#{story_num}"
    story_transitions_url = story_url+'/transitions?limit=3000'

    story_uri = URI(story_url)

    story_request = Net::HTTP::Get.new(story_uri)
    story_request["x-trackertoken"] = ENV['TRACKER_TOKEN']
    story_request["project"] = ENV['TRACKER_ID']
    story_request["cache-control"] = 'no-cache'

    story_response = http.request(story_request)

    story_info = JSON.parse(story_response.read_body)

    print "#{story_info['current_state']}"

    if story_info["current_state"] == "accepted"

      if story_info["story_type"] == "chore"
        accepted_stories += 1
      else
        story_transitions_uri = URI(story_transitions_url)
        story_transitions_request = Net::HTTP::Get.new(story_transitions_uri)
        story_transitions_request["x-trackertoken"] = ENV['TRACKER_TOKEN']
        story_transitions_request["project"] = ENV['TRACKER_ID']
        story_transitions_request["cache-control"] = 'no-cache'

        story_transitions_response = http.request(story_transitions_request)

        story_transitions = JSON.parse(story_transitions_response.read_body)

        story_transitions.reverse.each do |transistion|

          if transistion["state"] == "accepted"  # && approvers.include?(transistion["performed_by_id"].to_s)
            #print "... PM approved"
            accepted_stories += 1
            break
          end
        end
      end
    end
    print "\n"
  end
  print("\n")

  if accepted_stories != story_nums.length
    puts "Not all stories are in accepted status."
    exit 1
  else
    puts "All stories are in accepted status!"
    exit
  end
end

puts 'No stories flagged as finished, as there are no stories to check.'
puts 'Stopping pipeline'
exit(1)
