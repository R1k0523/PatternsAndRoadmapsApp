package ru.boringowl.parapp.presentation.repository.network.exceptions

import java.io.IOException
import java.lang.Exception

class NoInternetException : IOException() {

    override val message: String
        get() = "Отсутствует интернет"
}
class NoMoreInfoException : IOException() {

    override val message: String
        get() = "Новостей больше нет"
}
class NotFoundException : IOException() {

    override val message: String
        get() = "Не найдено"
}
class UnAuthorizedException : IOException() {

    override val message: String
        get() = "API ключ отсутствкет"
}
class UnknownException : Exception() {

    override val message: String
        get() = "Неизвестная ошибка"
}