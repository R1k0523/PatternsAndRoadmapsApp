package ru.boringowl.parapp.presentation.repository.mock

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import ru.boringowl.parapp.presentation.repository.PatternsRepository
import ru.boringowl.parapp.domain.model.patterns.PatternFeature
import ru.boringowl.parapp.domain.model.patterns.PatternInfo

class MockBase : PatternsRepository {
    var data: MutableLiveData<List<PatternInfo>>
    var list: List<PatternInfo>

    init {
        list = arrayListOf()
        val pattern1 = PatternInfo(
            title = "Абстрактная фабрика",
            description = "Позволяет создавать семейства связанных объектов, не привязываясь к конкретным классам создаваемых объектов.",
            problem = "Представьте, что вы пишете симулятор мебельного магазина. Ваш код содержит:\n" +
                    "1. Семейство зависимых продуктов. Скажем, Кресло + Диван + Столик.\n" +
                    "2. Несколько вариаций этого семейства. Например, продукты Кресло, Диван и Столик представлены в трёх разных стилях: Ар-деко, Викторианском и Модерне.\n" +
                    "Вам нужен такой способ создавать объекты продуктов, чтобы они сочетались с другими продуктами того же семейства. Это важно, так как клиенты расстраиваются, если получают несочетающуюся мебель.\n" +
                    "Кроме того, вы не хотите вносить изменения в существующий код при добавлении новых продуктов или семейcтв в программу. Поставщики часто обновляют свои каталоги, и вы бы не хотели менять уже написанный код каждый раз при получении новых моделей мебели.",
            solution = "Для начала паттерн Абстрактная фабрика предлагает выделить общие интерфейсы для отдельных продуктов, составляющих семейства. Так, все вариации кресел получат общий интерфейс Кресло, все диваны реализуют интерфейс Диван и так далее.\n" +
                    "Далее вы создаёте абстрактную фабрику — общий интерфейс, который содержит методы создания всех продуктов семейства (например, создатьКресло, создатьДиван и создатьСтолик). Эти операции должны возвращать абстрактные типы продуктов, представленные интерфейсами, которые мы выделили ранее — Кресла, Диваны и Столики.\n" +
                    "Как насчёт вариаций продуктов? Для каждой вариации семейства продуктов мы должны создать свою собственную фабрику, реализовав абстрактный интерфейс. Фабрики создают продукты одной вариации. Например, ФабрикаМодерн будет возвращать только КреслаМодерн,ДиваныМодерн и СтоликиМодерн.",
            solutionInCode = "// Этот паттерн предполагает, что у вас есть несколько семейств\n" +
                    "// продуктов, находящихся в отдельных иерархиях классов\n" +
                    "// (Button/Checkbox). Продукты одного семейства должны иметь\n" +
                    "// общий интерфейс.\n" +
                    "interface Button is\n" +
                    "    method paint()\n" +
                    "\n" +
                    "// Семейства продуктов имеют те же вариации (macOS/Windows).\n" +
                    "class WinButton implements Button is\n" +
                    "    method paint() is\n" +
                    "        // Отрисовать кнопку в стиле Windows.\n" +
                    "\n" +
                    "class MacButton implements Button is\n" +
                    "    method paint() is\n" +
                    "        // Отрисовать кнопку в стиле macOS.\n" +
                    "\n" +
                    "\n" +
                    "interface Checkbox is\n" +
                    "    method paint()\n" +
                    "\n" +
                    "class WinCheckbox implements Checkbox is\n" +
                    "    method paint() is\n" +
                    "        // Отрисовать чекбокс в стиле Windows.\n" +
                    "\n" +
                    "class MacCheckbox implements Checkbox is\n" +
                    "    method paint() is\n" +
                    "        // Отрисовать чекбокс в стиле macOS.\n" +
                    "\n" +
                    "\n" +
                    "// Абстрактная фабрика знает обо всех абстрактных типах\n" +
                    "// продуктов.\n" +
                    "interface GUIFactory is\n" +
                    "    method createButton():Button\n" +
                    "    method createCheckbox():Checkbox\n" +
                    "\n" +
                    "\n" +
                    "// Каждая конкретная фабрика знает и создаёт только продукты\n" +
                    "// своей вариации.\n" +
                    "class WinFactory implements GUIFactory is\n" +
                    "    method createButton():Button is\n" +
                    "        return new WinButton()\n" +
                    "    method createCheckbox():Checkbox is\n" +
                    "        return new WinCheckbox()\n" +
                    "\n" +
                    "// Несмотря на то, что фабрики оперируют конкретными классами,\n" +
                    "// их методы возвращают абстрактные типы продуктов. Благодаря\n" +
                    "// этому фабрики можно взаимозаменять, не изменяя клиентский\n" +
                    "// код.\n" +
                    "class MacFactory implements GUIFactory is\n" +
                    "    method createButton():Button is\n" +
                    "        return new MacButton()\n" +
                    "    method createCheckbox():Checkbox is\n" +
                    "        return new MacCheckbox()\n" +
                    "\n" +
                    "\n" +
                    "// Для кода, использующего фабрику, не важно, с какой конкретно\n" +
                    "// фабрикой он работает. Все получатели продуктов работают с\n" +
                    "// ними через общие интерфейсы.\n" +
                    "class Application is\n" +
                    "    private field factory: GUIFactory\n" +
                    "    private field button: Button\n" +
                    "    constructor Application(factory: GUIFactory) is\n" +
                    "        this.factory = factory\n" +
                    "    method createUI()\n" +
                    "        this.button = factory.createButton()\n" +
                    "    method paint()\n" +
                    "        button.paint()\n" +
                    "\n" +
                    "\n" +
                    "// Приложение выбирает тип конкретной фабрики и создаёт её\n" +
                    "// динамически, исходя из конфигурации или окружения.\n" +
                    "class ApplicationConfigurator is\n" +
                    "    method main() is\n" +
                    "        config = readApplicationConfigFile()\n" +
                    "\n" +
                    "        if (config.OS == \"Windows\") then\n" +
                    "            factory = new WinFactory()\n" +
                    "        else if (config.OS == \"Mac\") then\n" +
                    "            factory = new MacFactory()\n" +
                    "        else\n" +
                    "            throw new Exception(\"Error! Unknown operating system.\")\n" +
                    "\n" +
                    "        Application app = new Application(factory)",
            useCase = "Когда бизнес-логика программы должна работать с разными видами связанных друг с другом продуктов, не завися от конкретных классов продуктов.\n" +
                    "\n" +
                    " Когда в программе уже используется Фабричный метод, но очередные изменения предполагают введение новых типов продуктов.",
            feature = listOf(
                PatternFeature(
                    title = "Гарантирует сочетаемость создаваемых продуктов.",
                    featureType = PatternFeature.FeatureType.GOOD,
                ),
                PatternFeature(
                    title = "Избавляет клиентский код от привязки к конкретным классам продуктов.",
                    featureType = PatternFeature.FeatureType.GOOD,
                ),
                PatternFeature(
                    title = "Выделяет код производства продуктов в одно место, упрощая поддержку кода.",
                    featureType = PatternFeature.FeatureType.GOOD,
                ),
                PatternFeature(
                    title = "Усложняет код программы из-за введения множества дополнительных классов.",
                    featureType = PatternFeature.FeatureType.BAD,
                ),
                PatternFeature(
                    title = "Требует наличия всех типов продуктов в каждой вариации.",
                    featureType = PatternFeature.FeatureType.BAD,
                ),
            ),
            type = PatternInfo.PatternType.CREATIONAL,
            difficulty = 1,

        )
        val pattern2 = PatternInfo(
            title = "Адаптер",
            description = "Адаптер — это структурный паттерн проектирования, который позволяет объектам с несовместимыми интерфейсами работать вместе.",
            problem = "Представьте, что вы делаете приложение для торговли на бирже. Ваше приложение скачивает биржевые котировки из нескольких источников в XML, а затем рисует красивые графики.\n" +
                    "В какой-то момент вы решаете улучшить приложение, применив стороннюю библиотеку аналитики. Но вот беда — библиотека поддерживает только формат данных JSON, несовместимый с вашим приложением.\n" +
                    "Вы смогли бы переписать библиотеку, чтобы та поддерживала формат XML. Но, во-первых, это может нарушить работу существующего кода, который уже зависит от библиотеки. А во-вторых, у вас может просто не быть доступа к её исходному коду.",
            solution = "Представьте, что вы делаете приложение для торговли на бирже. Ваше приложение скачивает биржевые котировки из нескольких источников в XML, а затем рисует красивые графики.\n" +
                    "В какой-то момент вы решаете улучшить приложение, применив стороннюю библиотеку аналитики. Но вот беда — библиотека поддерживает только формат данных JSON, несовместимый с вашим приложением.\n" +
                    "Структура программы до подключения сторонней библиотеки\n" +
                    "Подключить стороннюю библиотеку не выйдет из-за несовместимых форматов данных.\n" +
                    "Вы смогли бы переписать библиотеку, чтобы та поддерживала формат XML. Но, во-первых, это может нарушить работу существующего кода, который уже зависит от библиотеки. А во-вторых, у вас может просто не быть доступа к её исходному коду.",
            solutionInCode = "// Классы с совместимыми интерфейсами: КруглоеОтверстие и\n" +
                    "// КруглыйКолышек.\n" +
                    "class RoundHole is\n" +
                    "    constructor RoundHole(radius) { ... }\n" +
                    "\n" +
                    "    method getRadius() is\n" +
                    "        // Вернуть радиус отверстия.\n" +
                    "\n" +
                    "    method fits(peg: RoundPeg) is\n" +
                    "        return this.getRadius() >= peg.getRadius()\n" +
                    "\n" +
                    "class RoundPeg is\n" +
                    "    constructor RoundPeg(radius) { ... }\n" +
                    "\n" +
                    "    method getRadius() is\n" +
                    "        // Вернуть радиус круглого колышка.\n" +
                    "\n" +
                    "\n" +
                    "// Устаревший, несовместимый класс: КвадратныйКолышек.\n" +
                    "class SquarePeg is\n" +
                    "    constructor SquarePeg(width) { ... }\n" +
                    "\n" +
                    "    method getWidth() is\n" +
                    "        // Вернуть ширину квадратного колышка.\n" +
                    "\n" +
                    "\n" +
                    "// Адаптер позволяет использовать квадратные колышки и круглые\n" +
                    "// отверстия вместе.\n" +
                    "class SquarePegAdapter extends RoundPeg is\n" +
                    "    private field peg: SquarePeg\n" +
                    "\n" +
                    "    constructor SquarePegAdapter(peg: SquarePeg) is\n" +
                    "        this.peg = peg\n" +
                    "\n" +
                    "    method getRadius() is\n" +
                    "        // Вычислить половину диагонали квадратного колышка по\n" +
                    "        // теореме Пифагора.\n" +
                    "        return peg.getWidth() * Math.sqrt(2) / 2\n" +
                    "\n" +
                    "\n" +
                    "// Где-то в клиентском коде.\n" +
                    "hole = new RoundHole(5)\n" +
                    "rpeg = new RoundPeg(5)\n" +
                    "hole.fits(rpeg) // TRUE\n" +
                    "\n" +
                    "small_sqpeg = new SquarePeg(5)\n" +
                    "large_sqpeg = new SquarePeg(10)\n" +
                    "hole.fits(small_sqpeg) // Ошибка компиляции, несовместимые типы\n" +
                    "\n" +
                    "small_sqpeg_adapter = new SquarePegAdapter(small_sqpeg)\n" +
                    "large_sqpeg_adapter = new SquarePegAdapter(large_sqpeg)\n" +
                    "hole.fits(small_sqpeg_adapter) // TRUE\n" +
                    "hole.fits(large_sqpeg_adapter) // FALSE",
            useCase = " Когда вы хотите использовать сторонний класс, но его интерфейс не соответствует остальному коду приложения.\n" +
                    "\n" +
                    " Когда вам нужно использовать несколько существующих подклассов, но в них не хватает какой-то общей функциональности, причём расширить суперкласс вы не можете.",
            feature = listOf(
                PatternFeature(
                    title = "Отделяет и скрывает от клиента подробности преобразования различных интерфейсов.",
                    featureType = PatternFeature.FeatureType.GOOD,
                ),
                PatternFeature(
                    title = "Усложняет код программы из-за введения дополнительных классов.",
                    featureType = PatternFeature.FeatureType.BAD,
                ),
            ),
            type = PatternInfo.PatternType.STRUCTURAL,
            difficulty = 0,

            )

        data = MutableLiveData(list)
    }

    override fun getAllPatternInfos(): LiveData<List<PatternInfo>?> {
        return data
    }

    override fun addPatternInfo(pattern: PatternInfo) {
        list = list + pattern
        data.value = list
    }

    override fun deletePatternInfo(pattern: PatternInfo) {
        list = list - pattern
        data.value = list
    }
}