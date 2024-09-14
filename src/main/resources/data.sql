
use `laboratory`;

-- INSERT Testing users
INSERT INTO userslab (id, email, name, firstname, password, role)
    VALUES 
    (1,'1@gmail.com', 'One', 'Ricky', '$2a$10$nf5N8Dhd4z3D9alrF6pH1e90o2DrTDG5VKSQ1Qy.dqnC/FVXB/UFm', 'ADMIN'),
    (2,'2@gmail.com', 'Two', 'Jacky', '$2a$10$cM3w.E7/gj3XVNYZpgrWLuuuArlmd2y96rM4Gn8Btx8uZ.w6Cfcl2', 'USER'),
    (3,'3@gmail.com', 'Three', 'Fify', '$2a$10$Mnpb8iFsYGEcAoI9V63yEOhEaEA6Cd8idEXy5L36nm.5Ns7xEuN5O', 'VIEWER'),
    (4,'4@gmail.com', 'Four', 'Dicky', '$2a$10$UT4nczbfwfnsnT.ktfggi.yrKTU1rocUmna2MTAHGIWvZS.O9QuRu', 'USER'),
    (5,'5@gmail.com', 'Five', 'Ly', '$2a$10$RjSF8oa6tYYtq9zQ02jkJ.mcbF6pGOUDP2A/nAnztWpca.nQNe7Ja', 'USER');