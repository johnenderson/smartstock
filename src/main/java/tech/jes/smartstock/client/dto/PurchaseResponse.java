package tech.jes.smartstock.client.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record PurchaseResponse(@JsonProperty("message") String message) {
}
