package com.bookmark.rest;

import com.bookmark.rest.mapper.BookmarkMapper;
import com.bookmark.rest.model.Bookmark;
import com.bookmark.rest.model.BookmarkDetails;
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
	public ResponseEntity<BookmarkDetails> getCategories() {
		return ResponseEntity.ok(BookmarkDetails.builder().bookmarks(BookmarkMapper.BOOKMARK_MAPPER
				.constructBookmark(requestBookmark.getBookmarks())).build());
	}

}
