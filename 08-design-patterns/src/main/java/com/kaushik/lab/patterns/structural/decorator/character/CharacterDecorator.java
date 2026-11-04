package com.kaushik.lab.patterns.structural.decorator.character;

abstract class CharacterDecorator extends Character {
    protected Character decoratedCharacter;

    public CharacterDecorator(Character decoratedCharacter) {
        this.decoratedCharacter = decoratedCharacter;
    }

    public abstract String getDescription();

    public abstract int getStats();
}
