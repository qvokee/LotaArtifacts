package com.lota.LotaArtifacts.item.curios;

import net.minecraft.ChatFormatting;
import net.minecraft.core.NonNullList;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.Tag;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.tags.ItemTags;
import net.minecraft.util.Mth;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.SlotAccess;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.ClickAction;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.inventory.tooltip.BundleTooltip;
import net.minecraft.world.inventory.tooltip.TooltipComponent;
import net.minecraft.world.item.*;
import net.minecraft.world.level.Level;
import top.theillusivec4.curios.api.SlotContext;
import top.theillusivec4.curios.api.type.capability.ICurioItem;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

public class BaseQuiverItem extends Item implements ICurioItem {
    public static final int MAX_WEIGHT = 64 * 3;

    public BaseQuiverItem(Properties properties) {
        super(properties);
    }

    @Override
    public List<Component> getAttributesTooltip(List<Component> tooltips, ItemStack stack) {
        return new ArrayList<>();
    }

    @Override
    public boolean canEquip(SlotContext slotContext, ItemStack stack) {
        String id = slotContext.identifier();
        return id.equals("back");
    }

    public static ItemStack getFirstArrow(ItemStack quiver) {
        return getContents(quiver).findFirst().orElse(ItemStack.EMPTY);
    }

    public static void consumeArrow(ItemStack quiver, boolean isInfinite) {
        if (isInfinite) {
            return;
        }

        CompoundTag compoundtag = quiver.getOrCreateTag();
        if (!compoundtag.contains("Items")) {
            return;
        }

        ListTag listtag = compoundtag.getList("Items", 10);
        if (listtag.isEmpty()) {
            return;
        }

        CompoundTag stackTag = listtag.getCompound(0);
        ItemStack arrowStack = ItemStack.of(stackTag);

        if (!arrowStack.isEmpty()) {
            arrowStack.shrink(1);

            if (arrowStack.isEmpty()) {
                listtag.remove(0);
                if (listtag.isEmpty()) {
                    quiver.removeTagKey("Items");
                }
            } else {
                CompoundTag newStackTag = new CompoundTag();
                arrowStack.save(newStackTag);
                listtag.set(0, newStackTag);
            }
        } else {
            listtag.remove(0);
            if (listtag.isEmpty()) {
                quiver.removeTagKey("Items");
            }
        }
    }

    @Override
    public boolean overrideStackedOnOther(ItemStack stack, Slot slot, ClickAction action, Player player) {
        if (action != ClickAction.SECONDARY) {
            return false;
        } else {
            ItemStack itemstack = slot.getItem();
            if (itemstack.isEmpty()) {
                this.playRemoveOneSound(player);
                removeOne(stack).ifPresent((removed) -> {
                    add(stack, slot.safeInsert(removed));
                });
            } else if (itemstack.getItem().canBeDepleted()) {
                int i = (MAX_WEIGHT - getContentWeight(stack)) / getWeight(itemstack);
                int j = add(stack, slot.safeTake(itemstack.getCount(), i, player));
                if (j > 0) {
                    this.playInsertSound(player);
                }
            }
            return true;
        }
    }

    @Override
    public boolean overrideOtherStackedOnMe(ItemStack stack, ItemStack other, Slot slot, ClickAction action,
            Player player, SlotAccess access) {
        if (action == ClickAction.SECONDARY && slot.allowModification(player)) {
            if (other.isEmpty()) {
                Optional<ItemStack> optional = removeOne(stack);
                if (optional.isPresent()) {
                    this.playRemoveOneSound(player);
                    access.set(optional.get());
                } else {
                }
            } else {
                int i = add(stack, other);
                if (i > 0) {
                    this.playInsertSound(player);
                    other.shrink(i);
                }
            }
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean isBarVisible(ItemStack stack) {
        return getContentWeight(stack) > 0;
    }

    @Override
    public int getBarWidth(ItemStack stack) {
        return Math.min(1 + 12 * getContentWeight(stack) / MAX_WEIGHT, 13);
    }

    @Override
    public int getBarColor(ItemStack stack) {
        return 0xFFFF55;
    }

    @Override
    public Optional<TooltipComponent> getTooltipImage(ItemStack stack) {
        NonNullList<ItemStack> nonnulllist = NonNullList.create();
        getContents(stack).forEach(nonnulllist::add);
        return Optional.of(new BundleTooltip(nonnulllist, getContentWeight(stack)));
    }

    @Override
    public void appendHoverText(ItemStack stack, Level level, List<Component> tooltip, TooltipFlag flag) {
        tooltip.add(Component.translatable("item.minecraft.bundle.fullness", getContentWeight(stack), MAX_WEIGHT)
                .withStyle(ChatFormatting.GRAY));
    }

    @Override
    public void onDestroyed(ItemEntity itemEntity) {
        ItemUtils.onContainerDestroyed(itemEntity, getContents(itemEntity.getItem()));
    }

    private static int add(ItemStack bundle, ItemStack item) {
        if (!item.isEmpty() && item.getItem().canFitInsideContainerItems() && canInsert(item)) {
            CompoundTag compoundtag = bundle.getOrCreateTag();
            if (!compoundtag.contains("Items")) {
                compoundtag.put("Items", new ListTag());
            }

            int currentWeight = getContentWeight(bundle);
            int itemWeight = getWeight(item);
            int spaceRemaining = MAX_WEIGHT - currentWeight;
            int countToAdd = Math.min(item.getCount(), spaceRemaining / itemWeight);

            if (countToAdd == 0) {
                return 0;
            } else {
                ListTag listtag = compoundtag.getList("Items", 10);
                int remainingToAdd = countToAdd;

                for (int i = 0; i < listtag.size(); i++) {
                    if (remainingToAdd <= 0)
                        break;
                    CompoundTag existingTag = listtag.getCompound(i);
                    ItemStack existingStack = ItemStack.of(existingTag);

                    if (ItemStack.isSameItemSameTags(existingStack, item)) {
                        int spaceInStack = Math.min(existingStack.getMaxStackSize(), 64) - existingStack.getCount();
                        int toMerge = Math.min(remainingToAdd, spaceInStack);

                        if (toMerge > 0) {
                            existingStack.grow(toMerge);
                            existingStack.save(existingTag);
                            remainingToAdd -= toMerge;
                            listtag.set(i, existingTag);
                        }
                    }
                }

                while (remainingToAdd > 0) {
                    int toAdd = Math.min(remainingToAdd, 64);
                    ItemStack newStack = item.copyWithCount(toAdd);
                    CompoundTag newTag = new CompoundTag();
                    newStack.save(newTag);
                    listtag.add(0, newTag);
                    remainingToAdd -= toAdd;
                }

                return countToAdd;
            }
        } else {
            return 0;
        }
    }

    private static Optional<CompoundTag> getMatchingItem(ItemStack stack, ListTag backingList) {
        if (stack.is(Items.BUNDLE)) {
            return Optional.empty();
        } else {
            for (int i = 0; i < backingList.size(); ++i) {
                CompoundTag compoundtag = backingList.getCompound(i);
                ItemStack itemstack = ItemStack.of(compoundtag);
                if (ItemStack.isSameItemSameTags(itemstack, stack)) {
                    return Optional.of(compoundtag);
                }
            }
            return Optional.empty();
        }
    }

    private static int getContentWeight(ItemStack stack) {
        return getContents(stack).mapToInt((item) -> {
            return getWeight(item) * item.getCount();
        }).sum();
    }

    private static int getWeight(ItemStack stack) {
        if (stack.is(Items.BUNDLE)) {
            return 4 + getContentWeight(stack);
        } else {
            return 64 / stack.getMaxStackSize();
        }
    }

    private static Stream<ItemStack> getContents(ItemStack stack) {
        CompoundTag compoundtag = stack.getTag();
        if (compoundtag == null) {
            return Stream.empty();
        } else {
            ListTag listtag = compoundtag.getList("Items", 10);
            return listtag.stream().map(CompoundTag.class::cast).map(ItemStack::of);
        }
    }

    private static Optional<ItemStack> removeOne(ItemStack stack) {
        CompoundTag compoundtag = stack.getOrCreateTag();
        if (!compoundtag.contains("Items")) {
            return Optional.empty();
        } else {
            ListTag listtag = compoundtag.getList("Items", 10);
            if (listtag.isEmpty()) {
                return Optional.empty();
            } else {
                int i = 0;
                CompoundTag compoundtag1 = listtag.getCompound(0);
                ItemStack itemstack = ItemStack.of(compoundtag1);
                listtag.remove(0);
                if (listtag.isEmpty()) {
                    stack.removeTagKey("Items");
                }

                return Optional.of(itemstack);
            }
        }
    }

    private static boolean canInsert(ItemStack stack) {
        return stack.is(ItemTags.ARROWS);
    }

    private void playRemoveOneSound(Entity entity) {
        entity.playSound(SoundEvents.BUNDLE_REMOVE_ONE, 0.8F, 0.8F + entity.level().getRandom().nextFloat() * 0.4F);
    }

    private void playInsertSound(Entity entity) {
        entity.playSound(SoundEvents.BUNDLE_INSERT, 0.8F, 0.8F + entity.level().getRandom().nextFloat() * 0.4F);
    }

    private void playDropContentsSound(Entity entity) {
        entity.playSound(SoundEvents.BUNDLE_DROP_CONTENTS, 0.8F, 0.8F + entity.level().getRandom().nextFloat() * 0.4F);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        ItemStack itemstack = player.getItemInHand(hand);
        if (dropContent(itemstack, player)) {
            this.playDropContentsSound(player);
            return InteractionResultHolder.sidedSuccess(itemstack, level.isClientSide());
        } else {
            return InteractionResultHolder.fail(itemstack);
        }
    }

    private static boolean dropContent(ItemStack stack, Player player) {
        CompoundTag compoundtag = stack.getOrCreateTag();
        if (!compoundtag.contains("Items")) {
            return false;
        } else {
            if (player instanceof net.minecraft.server.level.ServerPlayer) {
                ListTag listtag = compoundtag.getList("Items", 10);

                for (int i = 0; i < listtag.size(); ++i) {
                    CompoundTag compoundtag1 = listtag.getCompound(i);
                    ItemStack itemstack = ItemStack.of(compoundtag1);
                    player.drop(itemstack, true);
                }
            }

            stack.removeTagKey("Items");
            return true;
        }
    }
}
