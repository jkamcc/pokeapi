DROP TABLE IF EXISTS pokedata;

CREATE TABLE pokedata
(
    id              INT AUTO_INCREMENT PRIMARY KEY,
    name            VARCHAR(250) NOT NULL,
    height          INT          NOT NULL,
    weight          INT          NOT NULL,
    base_experience INT          NOT NULL
)
