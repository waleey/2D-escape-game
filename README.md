# 2D-escape-game

Overview: This game is basically an escaping game where there is a main character, a sprite
who can jump, duck, move right and left. The sole purpose of its life is to jump and duck to
escape the ghosts so that it doesn’t lose its life. Each time it collides with the ghosts, it loses its
health. If it jumps it will come down due to gravity, and when it ducks, it can go under the
passing ghosts without losing its health. We also kept the left and right movement of the player
but in the light of the game, these two moves don’t serve much. Unless you are assessing the
requirements to move in every direction, it is advisable that you only use the jump and duck
move to escape the ghosts. Nevertheless, each time one starts the game, the main character
will have 5 health count, which will decrease by one each time it collides with a ghost and it will
die if the health becomes zero. However, health supplies will be provided in each level of the
game through sending some “medipack”, glaring cross-like objects, and the player “must” collide
with the medipack to recharge its health to five.
The game has three different levels, called “Dance of The Dead'', “Demon Slayer”, and “No
Escape”. With each level, the difficulty to escape the ghosts increases. For instance, the ghosts
will appear faster, they will spawn at random locations in the screen-sometimes very close to the
player that the person playing the game needs a good reflex to escape it- and they’ll grow
bigger, requiring a very well-timed jump or duck to escape it. Finally, if someone makes it to the
final round, they will face multiple ghosts lurking in the screen and ready to eat the player.
Fortunately, however, as the level increases, the supply of medipacks will also increase, giving
the player a chance to replenish its health before facing the demons. With this supply, if
someone gets past the ghosts and passes the three insanely difficult levels, they will be
announced as the winners and given a “medal” of honor. However, honestly, none of the
creators of this game could make it easily to the end unless they started the game with a player
health of 500, which can be done by tweaking the code of course. Nevertheless, with each level,
the background image will change, giving the game and the player a nice feel. Also, the
melancholic background music adds a surreal experience to the mix.
Rules:
As soon as one runs the code, a menu will appear with ‘Play’ and ‘Quit’ button and they are just
what they sound like. In the ‘Play’, the player can hit ‘Space’ to jump and ‘Down’ arrow to duck.
To test the right and left move, ‘Right’ and ‘Left’ arrow keys should be pressed. The main
character also has an ‘attack’ mode which can be played by hitting the left mouse button, but it
does not serve any necessary purpose in the game.
Player score will be calculated based on how long the character can survive in the game and if
the character dies, the score will reset to zero, health will reset to 5, and a death screen will
appear. In the death screen, the player can either choose to quit the game by pushing the cross
or restart the game by pushing the home button. If the player becomes the winner, a winner
screen will appear which will have similar functionality to death screen. Each time a player dies
or wins, and wants to play more, he must start from the beginning, with zero score and 5 health.
Possible bugs:
● Once in about 15 runs, when someone replays the game after dying or winning, the
player spawns in a weird position and gets stuck there. In the latest update, we made
sure to spawn the player inside the screen and that seemed to work fine, but after many
runs, whether the problem might arise again or not is not entirely sure.
● If someone presses the ‘Down’ key while in the air due to a previous jump, the player
goes a bit further into the ground and it looks like the character's feet are inside the
ground. This problem, however, will go away as soon as the player presses ‘Spacebar’
to jump again. This is a very minor problem and the player might not even notice.
● Our Java System Library is JAVA SE-16. So, any compilation problem might arise, we
suggest checking the version you are using for running the code.
● It is a machine specific problem. On one of our teammate’s eclipse, once out of ten runs
the keylistener would not work or respond. This issue, however, can be immediately
fixed by compiling the program again.
Why do we think we fulfill all the requirements:
● Animation: The heart of our game lies in animation, where the main character is
animated using a 2D array of images. We used the subImage feature of BufferedImage
class to animate different versions of the character during different actions. It has
separate animation for running, jumping, falling, and attacking. Also, the ghosts are
animations of 6 images in a 1D array. Finally, the medipacks are also animation of
cross-like objects of 6 different colors, giving it a glaring appearance.
● Interactive: The UI and main game both are interactive. The Menu, Death Screen, and
Winner Screen all have options to either restart or quit the game through implementing
mouselistener. The main character control implements both keyboard listeners and
mouse listeners.
● Scoring: Scoring mechanism is implemented based on how long the player can survive
in the game. The score is basically how many times the frame updates while the player
survives and it's shown to the player on the top-right corner of the screen.
● Ending: The game can end two ways. The player might run out of health, which means
its health will become zero, which is also shown to the player on the top right corner, or
the player might pass all the levels and become an absolute winner. Depending on how
the game ends, different screens will appear to announce how they ended the game.
Moreover, there's an option to restart the game from those overlaid screens.
● Physical Mechanism: The player can move in all the directions. It can run right and left,
jump, and duck. While it jumps, the gravity acts on it to bring it to the ground. The
gravity also works as deceleration to decrease the speed after it jumps. The player runs
right and left with definite speed. The ghosts also have a certain speed which increases
and they start to appear faster as we go into the higher levels.
● Collision: The player can also collide with the ground, left and right boundaries, roof(if
the gravity is set to low and jump-speed is high enough to reach the roof), ghosts, and
the medipacks.
● Creative: We think the most creative part is how the difficulty of the level increases.
Especially, in the last level, there will be multiple ghosts on the screen at the same time.
When there was only one ghost, the condition we could use to spawn the next ghost was
just when the previous ghost left the screen. However, for multiple ghosts, we needed to
spawn a new ghost when the previous ghost was about 20% pixel away from the left
boundary while also drawing the previous ghost until it leaves the screen. This was tricky
and took us a while to figure out a working solution.
● Flourish: We will talk about it in detail in the next section.
Why do we think we have FIVE flourish:
● Randomized Objects: The ghosts spawn at random locations on the screen. Their
spawning index is obtained from the random integer generator. However, the random
numbers are generated in a range so that the ghosts are not too left on the screen and
they are not generated too high from the player so that it doesn’t even have to move to
escape it.
● Non-Stationary Objects: The medipacks are non-stationary objects that if hit by the
player replenish its health. It's also generated at a random location similarly to the
ghosts.
● Higher Levels: Another flourish is higher levels. The game has three different levels,
and with each level, the game difficulty and the background changes. In level 01, the
player will only face smaller and slower ghosts. In level 02, the ghosts will spawn closer,
move faster, and become bigger by the end of this level. In level 03, there will be multiple
big and bulky ghosts on the screen, so the player needs to be very precise about when
and where to jump and duck. These different effects in different levels took the bulk of
our time and we consider it as a strong flourish.
● Sound Effect: We also added a background sound that will loop continuously over the
course of the game using javax.sound.
● Making it Look Cool: We have used nifty graphics for the player and ghost animation
that doesn’t just look like boring geometric shapes. The background is also cool pixel art.
Class description:
● MainClass: Contains the main method to run the whole program
● Game: Contains the game loop, updates the game, and renders the game depending on
the game state.
● GamePanel: Sets up the JPanel.
● GameWindow: Sets up the JFrame and adds JPanel to the JFrame.
● Entity: Abstract class for all entities
● Ghost: Sets up the ghosts including updating and rendering, and load them in an array
● Medipack: Sets up the Medipack including updating and rendering, and load them in an
array
● Player: Sets the behavior of the player, collision, speed, movement, update, and
rendering.
● Gamestate: Enum listing all the game states: PLAYING, MENU, QUIT.
● Menu: Sets up the Menu game state
● Playing: Sets up the playing interface. It is the heart of the game.
● State: Helper class which helps switching between the game states using UI.
● Statmethods: Interface making sure each game state implements certain methods.
● KeyboardInputs
● MouseInputs
● SoundHandler
● GameOververlay: Sets up the winner screen once the player wins the game
● MenuButton: Sets up the buttons on Menu
● PauseButton: Top Level class for all the buttons
● PauseOverlay: Sets up the death screen
● UrmButton: Sets up the Home and Quit button on death and win screen.
● Constants: Contains the animation indexes to play the right player action
● HelpMethods: Contains static methods to detect collision and help movements.
● LoadSave: Contains static methods to load the images from the resource folder.
How to run: From inside the zip file, import the ‘ghost_escaper’ as a folder into your IDE. It
appears as src folder. Open Default packages, open MainClass and run it. All the graphics are
also in that ghost_escaper folder, and it should run properly.
NB: We acknowledge that we used some pixel arts downloaded from open source pixel art
websites like freepik. We also made some pixel arts using piskel.com open source software. All
other code is our own creation and we will be happy to explain any part of the code upon
request
