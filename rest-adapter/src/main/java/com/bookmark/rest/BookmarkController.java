package com.bookmark.rest;

import static com.bookmark.rest.mapper.BookmarkMapper.BOOKMARK_MAPPER;
import com.bookmark.rest.model.Bookmark;
import com.bookmark.rest.model.BookmarkDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.bookmark.domain.port.RequestBookmark;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/v1/bookmarks")
@CrossOrigin(origins = "http://localhost:4200")
public class BookmarkController {

	@Autowired
	private RequestBookmark requestBookmark;


	@GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<BookmarkDetails> getBookmarks() {
		return ResponseEntity.ok(BookmarkDetails.builder().bookmarks(BOOKMARK_MAPPER
				.constructBookmark(requestBookmark.getBookmarks())).build());
	}

	@PostMapping(value = "", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity createBookmark(@RequestBody Bookmark bookmark) {
		requestBookmark.save(BOOKMARK_MAPPER.constructBookmark(bookmark));
		return ResponseEntity.ok().build();
	}

	@GetMapping(value = "/{shortUrl}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Void> redirect(@PathVariable String shortUrl) {
		String url = requestBookmark.getOriginalURL(shortUrl);
		return ResponseEntity.status(HttpStatus.FOUND)
				.location(URI.create(url))
				.build();
	}
}
