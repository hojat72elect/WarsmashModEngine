package com.etheller.warsmash.viewer5.handlers.w3x.simulation.behaviors;

import com.etheller.warsmash.viewer5.handlers.w3x.simulation.CSimulation;
import com.etheller.warsmash.viewer5.handlers.w3x.simulation.CUnit;
import com.etheller.warsmash.viewer5.handlers.w3x.simulation.CWidget;
import com.etheller.warsmash.viewer5.handlers.w3x.simulation.abilities.CAbilityRanged;
import com.etheller.warsmash.viewer5.handlers.w3x.simulation.abilities.cargohold.CAbilityLoad;
import com.etheller.warsmash.viewer5.handlers.w3x.simulation.behaviors.CBehaviorMoveIntoRangeFor.PairAbilityLocator;

public class CBehaviorBoardTransport extends CBehaviorMoveIntoRangeFor implements PairAbilityLocator {

	public CBehaviorBoardTransport(final CUnit unit) {
		super(unit);
	}

	public CBehavior reset(final int higlightOrderId, final CWidget target) {
		return super.reset(higlightOrderId, target, this);
	}

	@Override
	public CAbilityRanged getPartnerAbility(final CSimulation game, final CUnit caster, final CUnit transport,
			final boolean ignoreRange, final boolean ignoreDisabled) {
		return CAbilityLoad.getTransportLoad(game, caster, transport, ignoreRange, ignoreDisabled);
	}
}
