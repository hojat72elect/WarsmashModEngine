package com.etheller.warsmash.viewer5.handlers.w3x.simulation.abilitybuilder.behavior.action;

import java.util.Map;

import com.etheller.warsmash.viewer5.handlers.w3x.simulation.CSimulation;
import com.etheller.warsmash.viewer5.handlers.w3x.simulation.CUnit;
import com.etheller.warsmash.viewer5.handlers.w3x.simulation.abilitybuilder.behavior.callback.floatcallbacks.ABFloatCallback;
import com.etheller.warsmash.viewer5.handlers.w3x.simulation.abilitybuilder.behavior.callback.idcallbacks.ABIDCallback;
import com.etheller.warsmash.viewer5.handlers.w3x.simulation.abilitybuilder.behavior.callback.unitcallbacks.ABUnitCallback;
import com.etheller.warsmash.viewer5.handlers.w3x.simulation.abilitybuilder.core.ABAction;
import com.etheller.warsmash.viewer5.handlers.w3x.simulation.abilitybuilder.core.ABLocalStoreKeys;
import com.etheller.warsmash.viewer5.handlers.w3x.simulation.util.SimulationRenderComponentLightning;

public class ABActionCreateLightningEffect implements ABAction {

	private ABUnitCallback origin;
	private ABUnitCallback target;
	private ABIDCallback id;
	private ABFloatCallback duration;

	public void runAction(final CSimulation game, final CUnit caster, final Map<String, Object> localStore,
			final int castId) {
		if (duration != null) {
			SimulationRenderComponentLightning ret = game.createLightning(
					origin.callback(game, caster, localStore, castId), this.id.callback(game, caster, localStore, castId),
					target.callback(game, caster, localStore, castId), duration.callback(game, caster, localStore, castId));
			localStore.put(ABLocalStoreKeys.LASTCREATEDLIGHTNING, ret);
		} else {
			SimulationRenderComponentLightning ret = game.createLightning(
					origin.callback(game, caster, localStore, castId), this.id.callback(game, caster, localStore, castId),
					target.callback(game, caster, localStore, castId));
			localStore.put(ABLocalStoreKeys.LASTCREATEDLIGHTNING, ret);
		}
	}
}
