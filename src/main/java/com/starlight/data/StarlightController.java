package com.starlight.data;

import java.io.*;
import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
@ComponentScan("com.starlight.data")
@RestController
public class StarlightController {
	@Autowired
	TweetRepository tweetrepository;
	
	@Autowired
	AuthorRepository authorrepository;
	
	@RequestMapping("/test")
	public String test() {
		return "test";
	}
	@RequestMapping("/androidrequest")
	public List<Tweet> androidRequest(Long authorid){
		Author author = authorrepository.findById(authorid).get();
		List<Tweet> tweets = tweetrepository.findByAuthor(author);
		return tweets;
	}
	@RequestMapping("/withauthor")
	public ModelAndView withauthor(Long authorid, Model model){
		Author author = authorrepository.findById(authorid).get();
		List<Tweet> tweets = tweetrepository.findByAuthor(author);
		model.addAttribute("tweets", tweets);
		return new ModelAndView("withauthor");
	}
	
	@RequestMapping("/index")
	public ModelAndView index(Model model) {
		List<Tweet> tweets = tweetrepository.findAllByOrderByIdDesc();
		
		model.addAttribute("tweets", tweets);
		return new ModelAndView("index");
	}
	@PostMapping("/index")
	public ModelAndView searchcontent(Model model, @RequestParam("contentsearch") String search) {
		List<Tweet> tweets = tweetrepository.findByContentLike("%"+search+"%");
		model.addAttribute("tweets",tweets);
		return new ModelAndView("searchresult");
	}
	@RequestMapping("/savetweet")
	public String saveTweet() {
		BufferedReader br = null;
		String dataurl = "C:\\Users\\Graphite\\eclipse-workspace\\StarlightTweet\\src\\main\\resources\\static\\tweets.txt";
		try {
			br = new BufferedReader(new InputStreamReader(new FileInputStream(dataurl),"gbk"));
			
			String contentline = br.readLine();
			
			while(contentline!=null) {
				String datas[] = contentline.split(" ");
				Optional<Author> a = authorrepository.findById((long) Integer.parseInt(datas[2]));
				Author author = a.get();
				Tweet tweet = new Tweet((long)Integer.parseInt(datas[0]),datas[1], author);
				tweetrepository.save(tweet);
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
				authorrepository.save(a);
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
	@RequestMapping("/delete")
	public String delele() {
		tweetrepository.deleteAll();
		return "delete completed";
	}
}
