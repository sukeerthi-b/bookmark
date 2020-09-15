package com.bookmark.rest;

import static com.bookmark.rest.mapper.BookmarkMapper.BOOKMARK_MAPPER;
import com.bookmark.rest.model.Bookmark;
import com.bookmark.rest.model.BookmarkDetails;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.bookmark.domain.port.RequestBookmark;

import java.net.URI;

@RestController
@RequestMapping("/api/v1/bookmarks")
@CrossOrigin(origins = "http://localhost:4200")
public class BookmarkController {

	private RequestBookmark requestBookmark;

	public BookmarkController(final RequestBookmark requestBookmark) {
		this.requestBookmark = requestBookmark;
	}

	@Operation(summary = "Get list of bookmarks")
	@ApiResponse(responseCode = "200", description = "Successfully retrieved the bookmarks")
	@ApiResponse(responseCode = "404", description = "Service not available")
	@GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<BookmarkDetails> getBookmarks() {
		return ResponseEntity.ok(BookmarkDetails.builder().bookmarks(BOOKMARK_MAPPER
				.constructBookmark(requestBookmark.getBookmarks())).build());
	}

	@Operation(summary = "Get list of bookmarks by group")
	@ApiResponse(responseCode = "200", description = "Successfully retrieved the bookmarks for given group")
	@ApiResponse(responseCode = "404", description = "Service not available")
	@GetMapping(value = "/{groupId}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<BookmarkDetails> getBookmarks(@Parameter(description = "id of the group to get bookmarks") @PathVariable Long groupId) {
		return ResponseEntity.ok(BookmarkDetails.builder().bookmarks(BOOKMARK_MAPPER
				.constructBookmark(requestBookmark.getBookmarks(groupId))).build());
	}

	@Operation(summary = "Create a new bookmark")
	@ApiResponse(responseCode = "200", description = "Successfully created the bookmark")
	@ApiResponse(responseCode = "404", description = "Service not available")
	@PostMapping(value = "", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity createBookmark(@RequestBody Bookmark bookmark) {
		requestBookmark.save(BOOKMARK_MAPPER.constructBookmark(bookmark));
		return ResponseEntity.ok().build();
	}

	@Operation(summary = "Redirect to original url")
	@ApiResponse(responseCode = "200", description = "Successfully redirected to the original url")
	@ApiResponse(responseCode = "404", description = "Service not available")
	@GetMapping(value = "redirect/{shortUrl}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Void> redirect(@Parameter(description = "short url") @PathVariable String shortUrl) {
		String url = requestBookmark.getOriginalURL(shortUrl);
		return ResponseEntity.status(HttpStatus.FOUND)
				.location(URI.create(url))
				.build();
	}
}
