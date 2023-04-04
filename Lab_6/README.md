# Laboratorul nr. 6

## Compulsory

Create the following components:

- The main frame of the application. (*solved*)
- A configuration panel for introducing parameters regarding the number of dots and lines and a button for creating a
  new game. The panel must be placed at the top part of the frame. The panel must contain at least one label and one
  input component. (*solved*)
- A canvas (drawing panel) for drawing the board. Draw the dots and the lines according to the values specified in the
  config panel. This panel must be placed in the center part of the frame. (*solved*)
- A control panel for managing the game. This panel will contain the buttons: Load, Save, Exit ,etc. and it will be
  placed at the bottom part of the frame. (*solved*)

## Homework

- Create the object oriented model of the game. Consider implementing a retained mode for drawing the game board. (
  *solved*)
- Implement the logic of the game. Use a *mouse listener* in order to select the line which must be colored, either by
  selecting the dots or the line itself. Validate the moves, according to the game rules. Determine the winner of the
  game. (*solved*)
- Export the current image of the game board into a PNG file. (*solved*)
- Use object serialization in order to save and restore the current status of the game. (*solved*)