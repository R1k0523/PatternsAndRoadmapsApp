package ru.boringowl.parapp.presentation.view

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ru.boringowl.parapp.R
import ru.boringowl.parapp.databinding.PatternsFragmentBinding
import ru.boringowl.parapp.domain.model.patterns.PatternFeature
import ru.boringowl.parapp.domain.model.patterns.PatternInfo
import ru.boringowl.parapp.presentation.repository.mock.MockBase
import ru.boringowl.parapp.presentation.view.adapters.PatternsListAdapter
import ru.boringowl.parapp.presentation.viewmodel.PatternsViewModel

class PatternsFragment : Fragment() {

    private lateinit var viewModel: PatternsViewModel
    private lateinit var binding : PatternsFragmentBinding
    //TODO to test Insert
    val pattern1 = PatternInfo(
        id = 1,
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
                isAdvantage = true,
            ),
            PatternFeature(
                title = "Избавляет клиентский код от привязки к конкретным классам продуктов.",
                isAdvantage = true,
            ),
            PatternFeature(
                title = "Выделяет код производства продуктов в одно место, упрощая поддержку кода.",
                isAdvantage = true,
            ),
            PatternFeature(
                title = "Усложняет код программы из-за введения множества дополнительных классов.",
                isAdvantage = false,
            ),
            PatternFeature(
                title = "Требует наличия всех типов продуктов в каждой вариации.",
                isAdvantage = false,
            ),
        ),
        type = PatternInfo.PatternType.CREATIONAL,
        difficulty = 1,

        )
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = PatternsFragmentBinding.inflate(layoutInflater, container, false)
        binding.patternsRecyclerView.layoutManager = LinearLayoutManager(context)
        ItemTouchHelper(object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {
            override fun onMove(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder, target: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val position = viewHolder.adapterPosition
                viewModel.deletePatternInfo(
                    (binding.patternsRecyclerView.adapter as PatternsListAdapter).data[position]
                )
            }
        }).attachToRecyclerView(binding.patternsRecyclerView)
        binding.floatingActionButton2.setOnClickListener {
            viewModel.addPatternInfo(pattern1)
            Log.d("size", viewModel.getPatternsList()?.value?.size.toString())

        }
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(PatternsViewModel::class.java)

        viewModel.getPatternsList()!!.observe(viewLifecycleOwner, {
            binding.patternsRecyclerView.adapter = PatternsListAdapter(it!!)
        })

    }
}