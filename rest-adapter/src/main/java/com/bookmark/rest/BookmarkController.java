package com.bookmark.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bookmark.domain.port.RequestBookmark;

@RestController
@RequestMapping("/api/v1/bookmark")
@CrossOrigin(origins = "http://localhost:4200")
public class BookmarkController {

	@Autowired
	private RequestBookmark requestBookmark;
}
