package ru.boringowl.parapp.presentation.repository.network.vacancies.response

import com.google.gson.annotations.SerializedName

data class Vacancy(@SerializedName("area")
                   val area: Area,
                   @SerializedName("name")
                   val name: String = "",
                   @SerializedName("employer")
                   val employer: Employer,
                   @SerializedName("salary")
                   val salary: Salary?,
                   @SerializedName("alternate_url")
                   val alternateUrl: String = "") {

    data class Area(@SerializedName("name")
                    val name: String = "",
                    @SerializedName("id")
                    val id: String = "")

    data class Employer(@SerializedName("name")
                        val name: String = "")

    data class Salary(@SerializedName("gross")
                      val gross: Boolean = false,
                      @SerializedName("from")
                      val from: Int? = null,
                      @SerializedName("currency")
                      val currency: String = "",
                      @SerializedName("to")
                      val to: Int? = null)

    fun salary() : String {
        return if (salary != null) if (salary.from != null)
            if (salary.to != null)
                "${salary.from}-${salary.to} ${salary.currency}"
            else
                "от ${salary.from} ${salary.currency}"
        else
            if (salary.to != null)
                "до ${salary.to} ${salary.currency}"
            else
                "Не указана"
        else
            "Не указана"
    }
}

