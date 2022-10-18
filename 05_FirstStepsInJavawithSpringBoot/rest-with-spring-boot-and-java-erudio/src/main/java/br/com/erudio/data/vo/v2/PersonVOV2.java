package br.com.erudio.data.vo.v2;

import java.io.Serializable;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class PersonVOV2 implements Serializable {

	private static final long serialVersionUID = -2466962960361270466L;
	
	private Long id;
	private String firstName;
	private String lastName;
	private String address;
	private String gender;
	private Date birthday;
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("PersonVOV2 [id=");
		builder.append(id);
		builder.append(", firstName=");
		builder.append(firstName);
		builder.append(", lastName=");
		builder.append(lastName);
		builder.append(", address=");
		builder.append(address);
		builder.append(", gender=");
		builder.append(gender);
		builder.append(", birthday=");
		builder.append(birthday);
		builder.append("]");
		return builder.toString();
	}
	
}