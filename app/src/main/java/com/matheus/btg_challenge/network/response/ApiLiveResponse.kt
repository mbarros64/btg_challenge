package com.matheus.btg_challenge.network.response

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class ApiLiveResponse {
    @SerializedName("success")
    var success : Boolean ?= null
    @SerializedName("quotes")
    @Expose
    var quotes : MutableMap<String, Double> ?= null
}