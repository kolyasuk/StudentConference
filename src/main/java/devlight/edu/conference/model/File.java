package devlight.edu.conference.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
@Table(name = "blob_file")
public class File implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3295901568640458637L;

	@Id
	@GeneratedValue
	private int id;

	@Column(name = "file_data")
	private byte[] fileData;

}
