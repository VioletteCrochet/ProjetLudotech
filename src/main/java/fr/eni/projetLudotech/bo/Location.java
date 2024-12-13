package fr.eni.projetLudotech.bo;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import jakarta.validation.constraints.NotNull;

public class Location {
	private Integer id;
	private LocalDate dateDebutLocation;
	private boolean Payed;
	private float TotalPrice;
	@NotNull
	private List<DetailLocation> details = new ArrayList<>();
	@NotNull
	private Client client;

	public Location(LocalDate dateDebutLocation, List<DetailLocation> details, Client client) {
		super();
		this.dateDebutLocation = dateDebutLocation;
		this.details = details;
		this.client = client;
	}

	public Location(Integer id, LocalDate dateDebutLocation, boolean Payed, float totalPrice,
			List<DetailLocation> details) {
		super();
		this.id = id;
		this.dateDebutLocation = dateDebutLocation;
		this.Payed = Payed;
		TotalPrice = totalPrice;
		this.details = details;
	}

	public Location() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Location(Integer id, LocalDate dateDebutLocation, boolean isPayed) {
		super();
		this.id = id;
		this.dateDebutLocation = dateDebutLocation;
		this.Payed = isPayed;
	}

	public void addDetail(DetailLocation detail) {
		this.details.add(detail);
	}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public LocalDate getDateDebutLocation() {
		return dateDebutLocation;
	}

	public void setDateDebutLocation(LocalDate dateDebutLocation) {
		this.dateDebutLocation = dateDebutLocation;
	}

	public boolean isPayed() {
		return Payed;
	}

	public void setPayed(boolean isPayed) {
		this.Payed = isPayed;
	}

	public float getTotalPrice() {
		return TotalPrice;
	}

	public void setTotalPrice(float totalPrice) {
		TotalPrice = totalPrice;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Location other = (Location) obj;
		return Objects.equals(id, other.id);
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Location [id=");
		builder.append(id);
		builder.append(", dateDebutLocation=");
		builder.append(dateDebutLocation);
		builder.append(", isPayed=");
		builder.append(Payed);
		builder.append(", TotalPrice=");
		builder.append(TotalPrice);
		builder.append("]");
		return builder.toString();
	}

	public List<DetailLocation> getDetails() {
		return details;
	}

	public void setDetails(List<DetailLocation> details) {
		this.details = details;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

}
