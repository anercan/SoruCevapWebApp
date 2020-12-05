package com.anercan.sorucevap.client.dto;

import com.anercan.sorucevap.enums.FilterStatus;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class DashboardDto {

    @NotNull
    FilterStatus filterStatus;

}
