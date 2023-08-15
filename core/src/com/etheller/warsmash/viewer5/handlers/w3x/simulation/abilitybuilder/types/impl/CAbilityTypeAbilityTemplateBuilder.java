package com.etheller.warsmash.viewer5.handlers.w3x.simulation.abilitybuilder.types.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.etheller.warsmash.util.War3ID;
import com.etheller.warsmash.viewer5.handlers.w3x.simulation.CSimulation;
import com.etheller.warsmash.viewer5.handlers.w3x.simulation.abilities.CAbility;
import com.etheller.warsmash.viewer5.handlers.w3x.simulation.abilities.generic.CLevelingAbility;
import com.etheller.warsmash.viewer5.handlers.w3x.simulation.abilities.types.CAbilityType;
import com.etheller.warsmash.viewer5.handlers.w3x.simulation.abilitybuilder.ability.template.CAbilityAbilityBuilderAuraTemplate;
import com.etheller.warsmash.viewer5.handlers.w3x.simulation.abilitybuilder.ability.template.CAbilityAbilityBuilderSimpleAuraTemplate;
import com.etheller.warsmash.viewer5.handlers.w3x.simulation.abilitybuilder.ability.template.CAbilityAbilityBuilderStatAuraTemplate;
import com.etheller.warsmash.viewer5.handlers.w3x.simulation.abilitybuilder.ability.template.CAbilityAbilityBuilderStatPassiveTemplate;
import com.etheller.warsmash.viewer5.handlers.w3x.simulation.abilitybuilder.core.ABLocalStoreKeys;
import com.etheller.warsmash.viewer5.handlers.w3x.simulation.abilitybuilder.parser.AbilityBuilderParser;

public class CAbilityTypeAbilityTemplateBuilder extends CAbilityType<CAbilityTypeAbilityBuilderLevelData>  {

	private AbilityBuilderParser parser;
	
	public CAbilityTypeAbilityTemplateBuilder(War3ID alias, War3ID code, List<CAbilityTypeAbilityBuilderLevelData> levelData, AbilityBuilderParser parser) {
		super(alias, code, levelData);
		this.parser = parser;
	}

	@Override
	public CAbility createAbility(int handleId) {
		Map<String, Object> localStore = new HashMap<>();
		localStore.put(ABLocalStoreKeys.LEVELDATA, getLevelData());
		localStore.put(ABLocalStoreKeys.CURRENTLEVEL, 1);
		localStore.put(ABLocalStoreKeys.ALIAS, getAlias());
		
		switch (parser.getTemplateType()) {
		case PASSIVE_STATS:
			return new CAbilityAbilityBuilderStatPassiveTemplate(handleId, getAlias(), getLevelData(), localStore, parser.getStatBuffsFromDataFields());
		case AURA_STATS:
			return new CAbilityAbilityBuilderStatAuraTemplate(handleId, getAlias(), getLevelData(), localStore, parser.getStatBuffsFromDataFields(), parser.getMeleeRangeTargetOverride());
		case AURA_SIMPLE:
			return new CAbilityAbilityBuilderSimpleAuraTemplate(handleId, getAlias(), getLevelData(), localStore, parser.getAbilityIdsToAddPerLevel(), parser.getLevellingAbilityIdsToAdd());
		case AURA:
		default:
			return new CAbilityAbilityBuilderAuraTemplate(handleId, getAlias(), getLevelData(), localStore, parser.getAddToAuraActions(), parser.getUpdateAuraLevelActions(), parser.getRemoveFromAuraActions());
		}
	}

	@Override
	public void setLevel(CSimulation game, CLevelingAbility existingAbility, int level) {
		existingAbility.setLevel(level);
	}

}
