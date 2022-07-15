package chapter13;

import java.time.LocalDateTime;

import org.springframework.format.annotation.DateTimeFormat;

public class ListRequest {
	@DateTimeFormat(pattern = "yyyy-MM-dd HH")
	private LocalDateTime from;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd HH")
	private LocalDateTime to;
	
	public LocalDateTime getFrom() {
		return from;
	}
	public void setFrom(LocalDateTime from) {
		this.from = from;
	}
	public LocalDateTime getTo() {
		return to;
	}
	public void setTo(LocalDateTime to) {
		this.to = to;
	}

}
