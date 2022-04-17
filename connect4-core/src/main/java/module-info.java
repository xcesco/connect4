module connect4.core {
    exports it.fmt.games.connect4;
    exports it.fmt.games.connect4.exceptions;
    exports it.fmt.games.connect4.model;
    exports it.fmt.games.connect4.model.cpu;
  exports it.fmt.games.connect4.model.operators;

  requires com.fasterxml.jackson.annotation;
}