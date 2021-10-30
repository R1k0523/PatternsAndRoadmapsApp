package ru.boringowl.parapp.domain.model.user

open class User(
    open var bio: String? = "",
    open var login: String? = "",
    open var company: String? = "",
    open var email: String? = "",
    open var url: String? = "",
    open var avatarUrl: String? = "",
    open var name: String? = "",
    open var location: String? = "",
    open var password: String? = "",
    open var blog: String? = ""
) {
    override fun toString(): String {
        return login+" "+email+" "+name
    }
}