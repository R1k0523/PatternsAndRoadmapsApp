package ru.boringowl.parapp.presentation.repository.model.user

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import ru.boringowl.parapp.domain.model.user.User
import java.util.*

@Entity(tableName = "users_table")
data class UserDTO(
    @ColumnInfo(name = "bio")
    override var bio: String? = "",
    @ColumnInfo(name = "login")
    override var login: String? = "",
    @ColumnInfo(name = "company")
    override var company: String? = "",
    @ColumnInfo(name = "email")
    override var email: String? = "",
    @ColumnInfo(name = "url")
    override var url: String? = "",
    @ColumnInfo(name = "avatarUrl")
    override var avatarUrl: String? = "",
    @ColumnInfo(name = "name")
    override var name: String? = "",
    @ColumnInfo(name = "location")
    override var location: String? = "",
    @ColumnInfo(name = "password")
    override var password: String? = "",
    @ColumnInfo(name = "blog")
    override var blog: String? = "",
    @ColumnInfo(name = "role")
    override var role: Roles = Roles.USER,
    @ColumnInfo(name = "user_id")
    override var userId: UUID? = null,
    @PrimaryKey(autoGenerate = true)
    var localId: Int? = null,
) : User(bio, login, company, email, url, avatarUrl, name, location, password, blog, role, userId) {

    constructor(user: User): this() {
        bio = user.bio
        login = user.login
        company = user.company
        email = user.email
        url = user.url
        avatarUrl = user.avatarUrl
        name = user.name
        location = user.location
        password = user.password
        blog = user.blog
        role = user.role
        userId = user.userId
    }
}