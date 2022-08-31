package br.inatel.myfreegameslibrary.exception;

import br.inatel.myfreegameslibrary.model.entity.Game;

public class GameNotFoundException extends RuntimeException {
    public GameNotFoundException(Game game) {

        super(String.format("Game '%s' was not found.", game.getTitle()));

    }
}
