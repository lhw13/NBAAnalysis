# -*- coding: utf-8 -*-
import urllib2
import random
import re
from bs4 import BeautifulSoup
import time

url2 = 'http://g.hupu.com/nba/daily/boxscore_150122.html'
url1 = 'http://g.hupu.com/nba/daily/playbyplay_150122.html'

# url4 = 'http://g.hupu.com/nba/daily/boxscore_150123.html'
# url3 = 'http://g.hupu.com/nba/daily/playbyplay_150123.html'

url4 = 'http://g.hupu.com/nba/daily/boxscore_151593.html'
url3 = 'http://g.hupu.com/nba/daily/playbyplay_151593.html'

my_headers = [
'Mozilla/5.0 (Windows NT 6.2; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/41.0.2272.3 Safari/537.36',
'Mozilla/4.0 (compatible; MSIE 7.0; Windows NT 5.1; Trident/4.0; SE 2.X MetaSr 1.0; SE 2.X MetaSr 1.0; .NET CLR 2.0.50727; SE 2.X MetaSr 1.0)',
'Mozilla/4.0 (compatible; MSIE 7.0; Windows NT 5.1; Trident/4.0; InfoPath.2; .NET4.0C; .NET4.0E; .NET CLR 2.0.50727; 360SE)',
'Mozilla/5.0 (Macintosh; U; Intel Mac OS X 10_6_8; en-us) AppleWebKit/534.50 (KHTML, like Gecko) Version/5.1 Safari/534.50',
'Mozilla/5.0 (Windows NT 6.1; rv:2.0.1) Gecko/20100101 Firefox/4.0.1'
]

def get_content(url,headers):
	"""
	@获取403禁止访问网页
	"""
	random_header = random.choice(headers)

	req = urllib2.Request(url)
	req.add_header('User-Agent',random_header)
	req.add_header('Host','g.hupu.com')
	req.add_header('Referer','http://g.hupu.com/')
	req.add_header('GET',url)

	content = urllib2.urlopen(req).read()
	return content

#获得直播信息
def get_Live(content):
	"""doc"""
	soup = BeautifulSoup(content)
	info = soup.find_all('div',"table_list_live table_overflow")
	return info[0]

def get_each(info):
	"""doc"""
	soup = BeautifulSoup(info)
	info = soup.find_all('td')
	nba_list = []
	for a in info:
		nba_list.append(str(a))

	path = "live.txt"
	f=open(path,'w')
	for ss in nba_list:
		f.write(ss+"\n")
	f.close()

# content_1 = get_content(url1, my_headers)
# get_each(str(get_Live(content_1)))

# content_2 = get_content(url2, my_headers)

# 获得球队和比分
def get_team(content):
	soup = BeautifulSoup(content)
	team_1 = soup.find_all('div','team_a')
	team_2 = soup.find_all('div','team_b')
	strlist = [str(team_1), str(team_2)]

	path = "team_score.txt"
	f=open(path,'w')
	for s in strlist:
		f.write(s+"\n")
	f.close()

# get_team(content_2)

# 获得每节比分
def get_partScore(content):
	soup = BeautifulSoup(content)
	info = soup.find_all('tbody')

	if len(info) >= 1:
		path = "each_part.txt"
		f=open(path,'w')
		f.write(str(info[0])+"\n")
		f.close()
	else:
		return "no partScore"

# get_partScore(content_2)

def get_detail(content):
	soup = BeautifulSoup(content)
	info = soup.find_all('tbody')

	path1 = "player_data_1.txt"
	path2 = "player_data_2.txt"

	write(path1,str(info[1]))
	write(path2,str(info[2]))

def write(path,info):
	f = open(path,'w')
	f.write(info)
	f.close()

# get_detail(content_2)

def lastMatch():
	content_1 = get_content(url1, my_headers)
	get_each(str(get_Live(content_1)))
	content_2 = get_content(url2, my_headers)
	get_team(content_2)
	get_partScore(content_2)
	get_detail(content_2)

content_2 = get_content(url4, my_headers)
if content_2 == "nothing":
	lastMatch()
else:
	result_1 = get_partScore(content_2)
	if result_1 == "no partScore":
		lastMatch()
	else:
		# get_team(content_2)
		get_detail(content_2)
		content_1 = get_content(url3, my_headers)
		get_each(str(get_Live(content_1)))
		

