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
    open var blog: String? = "",
    open var role: Roles = Roles.USER,
    open var id: Int? = null,
) {
    enum class Roles(val title: String) {
        ADMIN("admin"), MODERATOR("mod"), USER("user");
        fun getRole(title: String): Roles {
            return when(title) {
                "admin" -> ADMIN
                "mod" -> MODERATOR
                else -> USER
            }
        }
    }
}