package bulletin;

import java.util.Date;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

public class Placard {

	private Long id;
	private String message;
	private Date time;

	public Placard(String message, Date time) {
		this(null, message, time);
	}

	public Placard(Long id, String message, Date time) {
		this.id = id;
		this.message = message;
		this.time = time;
	}

	public long getId() {
		return id;
	}

	public String getMessage() {
		return message;
	}

	public Date getTime() {
		return time;
	}

	@Override
	public boolean equals(Object that) {
		return EqualsBuilder.reflectionEquals(this, that, "id", "time");
	}

	@Override
	public int hashCode() {
		return HashCodeBuilder.reflectionHashCode(this, "id", "time");
	}

	@Override
	public String toString() {
		return "Placard [id=" + id + ", message=" + message + ", time=" + time + "]";
	}

	// for PlacardController.savePlacard(Placard form)
	public Placard() {
	}
	public void setMessage(String message) {
		this.message = message;
	}
}
