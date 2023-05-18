package org.example.Decorator.CharacterDesignStudio;

abstract class CharacterDecorator extends Character {
    protected Character decoratedCharacter;

    public CharacterDecorator(Character decoratedCharacter) {
        this.decoratedCharacter = decoratedCharacter;
    }

    public abstract String getDescription();

    public abstract int getStats();
}
