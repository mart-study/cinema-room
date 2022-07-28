package com.movie.cinemaroom.dto.message;

import java.util.Date;
import java.util.Objects;

public class ErrorMessage {

	private int status;
	private Date errorDate = new Date();
	private String errorMessage;
	
	public ErrorMessage() {
		
	}

	public ErrorMessage(int status, String errorMessage) {
		this.status = status;
		this.errorMessage = errorMessage;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public Date getErrorDate() {
		return errorDate;
	}

	public void setErrorDate(Date errorDate) {
		this.errorDate = errorDate;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	@Override
	public int hashCode() {
		return Objects.hash(errorDate, errorMessage, status);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ErrorMessage other = (ErrorMessage) obj;
		return Objects.equals(errorDate, other.errorDate) && Objects.equals(errorMessage, other.errorMessage)
				&& status == other.status;
	}

	@Override
	public String toString() {
		return "ErrorMessage [status=" + status + ", errorDate=" + errorDate + ", errorMessage=" + errorMessage + "]";
	}

}
