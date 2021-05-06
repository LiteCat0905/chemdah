package ink.ptms.chemdah.core.quest.objective.bukkit

import ink.ptms.chemdah.core.quest.objective.Dependency
import ink.ptms.chemdah.core.quest.objective.ObjectiveCountableI
import org.bukkit.event.player.PlayerShearEntityEvent

/**
 * Chemdah
 * ink.ptms.chemdah.core.quest.objective.bukkit.IPlayerShear
 *
 * @author sky
 * @since 2021/3/2 5:09 下午
 */
@Dependency("minecraft")
object IPlayerShear : ObjectiveCountableI<PlayerShearEntityEvent>() {

    override val name = "player shear"
    override val event = PlayerShearEntityEvent::class

    init {
        handler {
            player
        }
        addCondition("position") { e ->
            toPosition().inside(e.entity.location)
        }
        addCondition("entity") { e ->
            toInferEntity().isEntity(e.entity)
        }
        addCondition("item") { e ->
            toInferItem().isItem(e.item)
        }
        addCondition("hand") { e ->
            asList().any { it.equals(e.hand.name, true) }
        }
    }
}