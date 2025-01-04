CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

INSERT INTO board_game (name, rating_score, description, rules_link)
SELECT *
FROM (VALUES
          ('Wingspan', NULL::real, 'A bird-themed strategy game where players collect and manage habitats to attract diverse species of birds.', 'https://www.wingspan.com/rules'),
          ('Ticket to Ride', NULL::real, 'A classic railway adventure game where players collect train cards to claim railway routes across maps.', 'https://www.tickettoride.com/rules'),
          ('Harmonies', NULL::real, 'A musical strategy game where players compose symphonies using resource management and card drafting.', 'https://www.harmoniesgame.com/rules'),
          ('Catan', NULL::real, 'A game of trade, building, and resource management where players settle and expand on an island.', 'https://www.catan.com/rules'),
          ('Carcassonne', NULL::real, 'A tile-placement game where players build medieval landscapes and score points for their features.', 'https://www.carcassonne.com/rules'),
          ('Root', NULL::real, 'A game of woodland warfare and asymmetrical strategy.', 'https://www.example.com/rules/root'),
          ('7 Wonders', NULL::real, 'A card-drafting game where players develop their civilization by building wonders and collecting resources.', 'https://www.7wonders.com/rules'),
          ('Azul', NULL::real, 'A decorative strategy game where players draft tiles to create beautiful mosaics.', 'https://www.azul.com/rules'),
          ('Dominion', NULL::real, 'A deck-building game where players compete to build the most effective deck of cards.', 'https://www.dominion.com/rules'),
          ('Scythe', NULL::real, 'A strategy game set in an alternate-history 1920s Europe with resource management and area control.', 'https://www.scythegame.com/rules'),
          ('Terraforming Mars', NULL::real, 'A futuristic strategy game where players compete to make Mars habitable while earning points.', 'https://www.terraformingmars.com/rules'),
          ('Gloomhaven', NULL::real, 'A cooperative dungeon-crawling adventure with evolving storylines and strategic combat.', 'https://www.gloomhaven.com/rules'),
          ('Splendor', NULL::real, 'A fast-paced card game where players collect gems to purchase developments and gain prestige.', 'https://www.splendor.com/rules'),
          ('Love Letter', NULL::real, 'A quick deduction card game where players compete to deliver their love letter to the princess.', 'https://www.lovelettergame.com/rules'),
          ('Dixit', NULL::real, 'A storytelling game where players use abstract art to convey hidden meanings and guess others’ interpretations.', 'https://www.dixitgame.com/rules')
     ) AS new_data(name, rating_score, description, rules_link)
WHERE NOT EXISTS (SELECT 1 FROM board_game LIMIT 1);

INSERT INTO rolls_dice_user (user_id, email, username, password, first_name, last_name)
SELECT *
FROM (VALUES
          (uuid_generate_v4(), 'johndoe@example.com', 'johndoe', 'password123', 'John', 'Doe'),
          (uuid_generate_v4(), 'janedoe@example.com', 'janedoe', 'securepass', 'Jane', 'Doe'),
          (uuid_generate_v4(), 'alexsmith@example.com', 'alexsmith', 'qwerty123', 'Alex', 'Smith'),
          (uuid_generate_v4(), 'emilybrown@example.com', 'emilybrown', 'mypassword', 'Emily', 'Brown'),
          (uuid_generate_v4(), 'michaeljohnson@example.com', 'michaeljohnson', 'letmein', 'Michael', 'Johnson')
     ) AS new_data(user_id, email, username, password, first_name, last_name)
WHERE NOT EXISTS(SELECT 1 FROM rolls_dice_user LIMIT 1);

INSERT INTO category (name)
SELECT *
FROM (VALUES
          ('Strategy'),
          ('Card Games'),
          ('Cooperative'),
          ('Family Games'),
          ('Party Games'),
          ('Abstract Games'),
          ('Role-Playing Games'),
          ('Miniature Games'),
          ('Deck-Building Games'),
          ('Educational'),
          ('Nature')
      ) AS new_data(name)
WHERE NOT EXISTS(SELECT 1 FROM category LIMIT 1);

INSERT INTO board_game_category (board_game_id, category_id)
SELECT *
FROM (VALUES
          (1, 1),
          (1, 2),
          (1, 4),
          (1, 11),
          (2, 1),
          (2, 4),
          (2, 6),
          (2, 8),
          (3, 1),
          (3, 6),
          (3, 10),
          (3, 4),
          (4, 1),
          (4, 4),
          (4, 6),
          (5, 1),
          (5, 4),
          (5, 6),
          (6, 1),
          (6, 3),
          (6, 4),
          (6, 8),
          (7, 1),
          (7, 2),
          (7, 6),
          (7, 9),
          (8, 1),
          (8, 6),
          (8, 4),
          (9, 1),
          (9, 9),
          (9, 2),
          (10, 1),
          (10, 6),
          (10, 8),
          (11, 1),
          (11, 2),
          (11, 6),
          (12, 1),
          (12, 3),
          (12, 7),
          (12, 8),
          (13, 1),
          (13, 2),
          (13, 6),
          (14, 1),
          (14, 2),
          (14, 5),
          (15, 1),
          (15, 6),
          (15, 5)
      ) AS new_data(board_game_id, category_id)
WHERE NOT EXISTS(SELECT 1 FROM board_game_category LIMIT 1);