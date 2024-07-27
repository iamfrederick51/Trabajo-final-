package logico;

import java.io.Serializable;

public class Review implements Serializable {
    private static final long serialVersionUID = 1L;
    private String customerName;
    private String comment;
    private int rating; // 1-5

    public Review(String customerName, String comment, int rating) {
        this.customerName = customerName;
        this.comment = comment;
        this.rating = rating;
    }

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public int getRating() {
		return rating;
	}

	public void setRating(int rating) {
		this.rating = rating;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}