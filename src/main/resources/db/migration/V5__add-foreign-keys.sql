ALTER TABLE topicos
ADD CONSTRAINT fk_topicos_autor_id
FOREIGN KEY (autor_id) REFERENCES usuarios(id);

ALTER TABLE topicos
ADD CONSTRAINT fk_topicos_curso_id
FOREIGN KEY (curso_id) REFERENCES cursos(id);

ALTER TABLE respuestas
ADD CONSTRAINT fk_respuestas_autor_id
FOREIGN KEY (autor_id) REFERENCES usuarios(id);

ALTER TABLE respuestas
ADD CONSTRAINT fk_respuestas_topico_id
FOREIGN KEY (topico_id) REFERENCES topicos(id);

ALTER TABLE usuario_perfiles
ADD CONSTRAINT fk_usuarioperfiles_usuario_id
FOREIGN KEY (usuario_id) REFERENCES usuarios(id);

ALTER TABLE usuario_perfiles
ADD CONSTRAINT fk_usuarioperfiles_perfil_id
FOREIGN KEY (perfil_id) REFERENCES perfiles(id);