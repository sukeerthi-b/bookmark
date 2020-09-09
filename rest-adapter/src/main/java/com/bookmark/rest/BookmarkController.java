package com.bookmark.rest;

import com.bookmark.domain.model.Bookmark;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bookmark.domain.port.RequestBookmark;

import java.util.List;

@RestController
@RequestMapping("/api/v1/bookmark")
@CrossOrigin(origins = "http://localhost:4200")
public class BookmarkController {

	@Autowired
	private RequestBookmark requestBookmark;


	@GetMapping("bookmarks")
	public ResponseEntity<List<Bookmark>> getCategories() {
		return ResponseEntity.ok(requestBookmark.getBookmarks());
	}

}
