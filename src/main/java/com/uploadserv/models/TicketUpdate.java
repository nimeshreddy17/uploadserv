package com.uploadserv.models;


import java.util.HashMap;
import java.util.Map;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyDescription;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonValue;


/**
 * Ticket Update resource
 * <p>
 * Ticket Update resource object
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "pnr",
        "first_name",
        "last_name",
        "fare_class",
        "travel_date",
        "pax",
        "ticketing_date",
        "email",
        "mobile_hone",
        "booked_cabin"
})
public class TicketUpdate {

    /**
     * pnr
     */
    @JsonProperty("pnr")
    @JsonPropertyDescription("pnr")
    @Pattern(regexp = "^[a-zA-Z0-9]{6}$")
    @Size(min = 6, max = 6)
    private String pnr;
    /**
     * first name
     */
    @JsonProperty("first_name")
    @JsonPropertyDescription("first name")
    @Size(min = 1, max = 15)
    private String firstName;
    /**
     * last name
     */
    @JsonProperty("last_name")
    @JsonPropertyDescription("last name")
    @Size(min = 1, max = 15)
    private String lastName;
    /**
     * fare class
     */
    @JsonProperty("fare_class")
    @JsonPropertyDescription("fare class")
    @Pattern(regexp = "^[A-Z]$")
    @Size(min = 1, max = 1)
    private String fareClass;
    /**
     * travel date
     */
    @JsonProperty("travel_date")
    @JsonPropertyDescription("travel date")
    @Pattern(regexp = "^\\d{4}\\-(0?[1-9]|1[012])\\-(0?[1-9]|[12][0-9]|3[01])$")
    private String travelDate;
    /**
     * number of passengers
     */
    @JsonProperty("pax")
    @JsonPropertyDescription("number of passengers")
    private Integer pax;
    /**
     * Date of Booking
     */
    @JsonProperty("ticketing_date")
    @JsonPropertyDescription("Date of Booking")
    @Pattern(regexp = "^\\d{4}\\-(0?[1-9]|1[012])\\-(0?[1-9]|[12][0-9]|3[01])$")
    private String ticketingDate;
    /**
     * Email
     */
    @JsonProperty("email")
    @JsonPropertyDescription("Email")
    @Pattern(regexp = "^(.+)@(.+)$")
    private String email;
    /**
     * Mobile phone
     */
    @JsonProperty("mobile_phone")
    @JsonPropertyDescription("Mobile phone")
    @Pattern(regexp = "^\\d{10}$")
    private String mobilePhone;
    /**
     * Booked Cabin
     * (Required)
     */
    @JsonProperty("booked_cabin")
    @JsonPropertyDescription("Booked Cabin")
    @NotNull
    private TicketUpdate.BookedCabin bookedCabin;
    @JsonIgnore
    @Valid
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * pnr
     */
    @JsonProperty("pnr")
    public String getPnr() {
        return pnr;
    }

    /**
     * pnr
     */
    @JsonProperty("pnr")
    public void setPnr(String pnr) {
        this.pnr = pnr;
    }

    /**
     * first name
     */
    @JsonProperty("first_name")
    public String getFirstName() {
        return firstName;
    }

    /**
     * first name
     */
    @JsonProperty("first_name")
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * last name
     */
    @JsonProperty("last_name")
    public String getLastName() {
        return lastName;
    }

    /**
     * last name
     */
    @JsonProperty("last_name")
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * fare class
     */
    @JsonProperty("fare_class")
    public String getFareClass() {
        return fareClass;
    }

    /**
     * fare class
     */
    @JsonProperty("fare_class")
    public void setFareClass(String fareClass) {
        this.fareClass = fareClass;
    }

    /**
     * travel date
     */
    @JsonProperty("travel_date")
    public String getTravelDate() {
        return travelDate;
    }

    /**
     * travel date
     */
    @JsonProperty("travel_date")
    public void setTravelDate(String travelDate) {
        this.travelDate = travelDate;
    }

    /**
     * number of passengers
     */
    @JsonProperty("pax")
    public Integer getPax() {
        return pax;
    }

    /**
     * number of passengers
     */
    @JsonProperty("pax")
    public void setPax(Integer pax) {
        this.pax = pax;
    }

    /**
     * Date of Booking
     */
    @JsonProperty("ticketing_date")
    public String getTicketingDate() {
        return ticketingDate;
    }

    /**
     * Date of Booking
     */
    @JsonProperty("ticketing_date")
    public void setTicketingDate(String ticketingDate) {
        this.ticketingDate = ticketingDate;
    }

    /**
     * Email
     */
    @JsonProperty("email")
    public String getEmail() {
        return email;
    }

    /**
     * Email
     */
    @JsonProperty("email")
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Mobile phone
     */
    @JsonProperty("mobile_phone")
    public String getMobilePhone() {
        return mobilePhone;
    }

    /**
     * Mobile phone
     */
    @JsonProperty("mobile_hone")
    public void setMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone;
    }

    /**
     * Booked Cabin
     * (Required)
     */
    @JsonProperty("booked_cabin")
    public TicketUpdate.BookedCabin getBookedCabin() {
        return bookedCabin;
    }

    /**
     * Booked Cabin
     * (Required)
     */
    @JsonProperty("booked_cabin")
    public void setBookedCabin(TicketUpdate.BookedCabin bookedCabin) {
        this.bookedCabin = bookedCabin;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(TicketUpdate.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("pnr");
        sb.append('=');
        sb.append(((this.pnr == null) ? "<null>" : this.pnr));
        sb.append(',');
        sb.append("firstName");
        sb.append('=');
        sb.append(((this.firstName == null) ? "<null>" : this.firstName));
        sb.append(',');
        sb.append("lastName");
        sb.append('=');
        sb.append(((this.lastName == null) ? "<null>" : this.lastName));
        sb.append(',');
        sb.append("fareClass");
        sb.append('=');
        sb.append(((this.fareClass == null) ? "<null>" : this.fareClass));
        sb.append(',');
        sb.append("travelDate");
        sb.append('=');
        sb.append(((this.travelDate == null) ? "<null>" : this.travelDate));
        sb.append(',');
        sb.append("pax");
        sb.append('=');
        sb.append(((this.pax == null) ? "<null>" : this.pax));
        sb.append(',');
        sb.append("ticketingDate");
        sb.append('=');
        sb.append(((this.ticketingDate == null) ? "<null>" : this.ticketingDate));
        sb.append(',');
        sb.append("email");
        sb.append('=');
        sb.append(((this.email == null) ? "<null>" : this.email));
        sb.append(',');
        sb.append("mobilePhone");
        sb.append('=');
        sb.append(((this.mobilePhone == null) ? "<null>" : this.mobilePhone));
        sb.append(',');
        sb.append("bookedCabin");
        sb.append('=');
        sb.append(((this.bookedCabin == null) ? "<null>" : this.bookedCabin));
        sb.append(',');
        sb.append("additionalProperties");
        sb.append('=');
        sb.append(((this.additionalProperties == null) ? "<null>" : this.additionalProperties));
        sb.append(',');
        if (sb.charAt((sb.length() - 1)) == ',') {
            sb.setCharAt((sb.length() - 1), ']');
        } else {
            sb.append(']');
        }
        return sb.toString();
    }


    /**
     * Booked Cabin
     */
    public enum BookedCabin {

        ECONOMY("Economy"),
        PREMIUM_ECONOMY("Premium Economy"),
        BUSINESS("Business"),
        FIRST("First");
        private final String value;
        private final static Map<String, TicketUpdate.BookedCabin> CONSTANTS = new HashMap<String, TicketUpdate.BookedCabin>();

        static {
            for (TicketUpdate.BookedCabin c : values()) {
                CONSTANTS.put(c.value, c);
            }
        }

        private BookedCabin(String value) {
            this.value = value;
        }

        @Override
        public String toString() {
            return this.value;
        }

        @JsonValue
        public String value() {
            return this.value;
        }

        @JsonCreator
        public static TicketUpdate.BookedCabin fromValue(String value) {
            TicketUpdate.BookedCabin constant = CONSTANTS.get(value);
            if (constant == null) {
                throw new IllegalArgumentException(value);
            } else {
                return constant;
            }
        }

    }

}
