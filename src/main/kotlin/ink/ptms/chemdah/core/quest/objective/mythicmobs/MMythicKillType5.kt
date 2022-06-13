package ink.ptms.chemdah.core.quest.objective.mythicmobs

import ink.ptms.chemdah.core.quest.objective.ObjectiveCountableI
import io.lumine.mythic.bukkit.events.MythicMobDeathEvent
import org.bukkit.entity.Player

class MMythicKillType5 : ObjectiveCountableI<MythicMobDeathEvent>() {

    override val name = "mythicmobs kill"
    override val event = MythicMobDeathEvent::class.java

    init {
        handler {
            it.killer as? Player
        }
        addSimpleCondition("position") { data, e ->
            data.toPosition().inside(e.killer.killer!!.location)
        }
        addSimpleCondition("name") { data, e ->
            data.asList().any { it.equals(e.mobType.internalName, true) }
        }
        addSimpleCondition("level") { data, e ->
            data.toDouble() == e.mobLevel
        }
        addSimpleCondition("min-level") { data, e ->
            data.toDouble() <= e.mobLevel
        }
        addConditionVariable("name") {
            it.mobType.internalName
        }
    }
}
