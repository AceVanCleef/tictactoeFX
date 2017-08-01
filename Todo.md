# TODOs
- Scoe doesn't get stored permanently. Think about how to handle score when new player is added aswell.
- GameBoard's background width is stretched due to Header's width. Center it?
- Notify all Players (@GUI) how the game has ended (Player 1 wins, Player 2 wins, The game ended in a draw)
  
  Idea: change background color accordingly: player 1 wins -> BGColor of Player01's section changes. Draw? Bgcolor of center section changes.
- change ScoreView - simple control from HBox to a TableView

- When a new game should be started but the value in amountOfPlayersTF hasn't changed, prevent a reset of player scores, the simples way would be to avoid allPlayers.clear(9 and also not adding new PlayerPMs to allPlayers. Also, don't forget to do the same for drawCount. 