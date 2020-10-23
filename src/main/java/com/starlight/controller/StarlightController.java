package com.starlight.controller;

import java.io.*;
import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import com.starlight.mapper.AuthorMapper;
import com.starlight.mapper.TextureMapper;
import com.starlight.mapper.TweetMapper;
import com.starlight.model.*;
@ComponentScan("com.starlight.model")
@ComponentScan("com.starlight.mapper")
@RestController
public class StarlightController {
	@Autowired
	AuthorMapper authormapper;
	@Autowired
	TweetMapper tweetmapper;
	@Autowired
	TextureMapper texturemapper;
	@Autowired
	PagePlugin pageplugin;
	@Autowired
	TweetPage tweetpage;
	
	@RequestMapping("/test")
	public String test() {
		return "test";
	}
	@RequestMapping("/withauthor")
	public List<Tweet> withauthor(long authorid){
		List<Tweet> tweets = tweetmapper.getTweetByAuthor(authorid);
		return tweets;
	}
	@RequestMapping("/index")
	public TweetPage index(int page) {
		Properties properties = new Properties();
		properties.setProperty("page", String.valueOf(-1));
		pageplugin.setProperties(properties);
		int count = tweetmapper.getCount();
		tweetpage.setPageCount(count/PagePlugin.count+1);
        
		properties.setProperty("page", String.valueOf(page));
        pageplugin.setProperties(properties);
		List<Tweet> tweets = tweetmapper.getAllByOrderByIdPage();
		tweetpage.setPage(page);
		tweetpage.setContent(tweets);
		return tweetpage;
	}
	@PostMapping("/index")
	public List<Tweet> searchcontent(String search) {
		List<Tweet> tweets = tweetmapper.getByContent("%"+search+"%");
		return tweets;
	}
	@RequestMapping("/savetauthor")
	public String saveAuthor() {
		BufferedReader br = null;
		String dataurl = "C:\\Users\\Graphite\\eclipse-workspace\\StarlightTweet\\src\\main\\resources\\static\\authors.txt";
		try {
			br = new BufferedReader(new InputStreamReader(new FileInputStream(dataurl),"gbk"));
			
			String contentline = br.readLine();
			
			while(contentline!=null) {
				String datas[] = contentline.split(" ");
				Author a = new Author((long)Integer.parseInt(datas[0]), datas[1], "starlight", datas[2]);
				authormapper.save(a);
				contentline = br.readLine();
			}
		}catch(IOException ex) {
			ex.printStackTrace();
		}finally {
			if(br!=null)
				try {
					br.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
		return "completed";
	}
	/*
	@RequestMapping("/delete")
	public String delele() {
		tweetmapper.deleteAll();
		return "delete completed";
	}*/
}
