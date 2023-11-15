package com.neusoft.elmboot.entity;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Business implements Serializable {
        private static final long serialVersionUID = -3387517072650584105L;
		private Integer businessId;
        private String businessName;
        private String businessAddress;
        private String businessExplain;
        private String businessImg;
        private Integer orderTypeId;
        private double starPrice; //起送费
        private double deliveryPrice; //配送费
        private Integer hot;
}
