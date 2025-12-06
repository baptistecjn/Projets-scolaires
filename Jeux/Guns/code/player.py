import pygame
import time
import numpy as np
import math
from particles import ParticleEffect
from hook import Hook

class Player(pygame.sprite.Sprite):
	def __init__(self,pos):
		super().__init__()
		self.image = pygame.image.load('../graphics/2.png').convert_alpha()
		self.rect = self.image.get_rect(topleft = pos)
		self.mask = pygame.mask.from_surface(self.image)
		

		self.gun_image = pygame.transform.scale(pygame.image.load('../graphics/gun_ange.png'), (128, 128)).convert_alpha()
		self.grp_fleche = pygame.sprite.Group()
		self.particles_effects = pygame.sprite.Group()

		self.last_debug_time = time.time()
		self.last_jump_debug_time = time.time()

		# player movement
		self.direction = pygame.math.Vector2(0,0)
		self.speed = 8
		self.gravity = 0.8
		self.jump_speed = -16
		self.jump_count = 0

		self.can_shoot = False
		self.cooldown = 0

		self.grappling = False # True si actionné 
		self.hook = Hook(350)

		# player status
		self.facing_right = True
		self.on_ground = False
		self.on_ceiling = False
		self.on_left = False
		self.on_right = False
		self.jump_key_released = True

	def get_gun_position(self):
		return self.rect.center
	
	def get_gun_angle(self):
		gun_position = self.get_gun_position()
		mouse_x, mouse_y = pygame.mouse.get_pos()
		angle = math.degrees(math.atan2(mouse_y - gun_position[1], mouse_x - gun_position[0]))
		return angle
	
	def draw_gun(self, window):
		gun_position = self.get_gun_position()
		angle = self.get_gun_angle()
		rotated_gun = pygame.transform.rotate(self.gun_image, -angle)
		rotated_rect = rotated_gun.get_rect(center=gun_position)
		window.blit(rotated_gun, rotated_rect)

	def get_input(self):
		keys = pygame.key.get_pressed()
		mouse_buttons = pygame.mouse.get_pressed()

		# Limit print statements to once per second
		current_time = time.time()
		if current_time - self.last_debug_time > 0.24:
			# print(self.hook.has_attached)
			self.last_debug_time = current_time
   
		if mouse_buttons[0] and self.cooldown==0:
			self.cooldown = 25
			self.can_shoot = True
		else:
			self.can_shoot = False
		if mouse_buttons[2]:
			self.grappling = True
			self.hook.is_being_thrown = True
		else:
			self.grappling = False
			self.hook.retract()

		if keys[pygame.K_d]:
			self.direction.x = 1 #if not self.hook.has_attached else 0.5  # valeur en grappin à tester
			self.facing_right = True
		elif keys[pygame.K_q]:
			self.direction.x = -1 #if not self.hook.has_attached else -0.5  # valeur en grappin à tester
			self.facing_right = False
		else:
			self.direction.x = 0

		if (keys[pygame.K_SPACE] or keys[pygame.K_z]):
			if self.jump_key_released:  # Check if the jump key was just pressed
				self.jump()
				self.jump_key_released = False  # The jump key is now being held down
		else:
			self.jump_key_released = True

	def jump(self):
		if self.jump_count < 2:
			self.direction.y = -15
			self.jump_count += 1
			self.on_ground = False
			if self.jump_count == 2:
				particle_effect = ParticleEffect(self.rect.midbottom, 'jump')
				self.particles_effects.add(particle_effect)
	
	def shoot(self):
		if self.can_shoot:
			self.fleche = Fleche(int(self.rect.centerx + (1.5 * self.rect.size[0] * self.direction.x)), self.rect.centery, pygame.mouse.get_pos())
			self.grp_fleche.add(self.fleche)

	def apply_gravity(self):
		constant = 0.1  # à fixer
		if not self.hook.has_attached:
			self.direction.y += self.gravity
			self.rect.y += self.direction.y
	
	def hook_movement(self):
		if self.grappling and self.hook.has_attached:
			speed = 15

			cx, cy = self.hook.collision_point
			px, py = self.rect.x + self.rect.width / 2, self.rect.y + self.rect.height / 2  # Coord center
			
			# vecteur direction et sa norme
			dx, dy = cx - px, cy - py
			norm = math.sqrt(dx**2 + dy**2)

			# vecteur unitaire * vitesse
			if norm > 0:
				dx, dy = (dx / norm) * speed, (dy / norm) * speed

			self.rect.x += dx
			self.rect.y += dy

	def update(self,window):
		self.get_input()
		self.draw_gun(window)
		self.grp_fleche.update()			
		self.grp_fleche.draw(window)
		self.shoot()
		self.hook.set_gun_angle(self.get_gun_angle())
		self.hook.update(window, self.rect.center)
		#print(self.hook.has_attached)
		self.hook_movement()
		self.particles_effects.update(self.rect.x)
		self.particles_effects.draw(window)
		if self.cooldown>0:
			self.cooldown-=1

		# Visualize player mask
		"""mask_surface = pygame.Surface(self.image.get_size(), pygame.SRCALPHA)
		mask = pygame.mask.from_surface(self.image)
		width, height = mask.get_size()
		for x in range(width):
			for y in range(height):
				if mask.get_at((x, y)):
					mask_surface.set_at((x, y), (255, 0, 0, 255))  # Red color for mask pixels
		window.blit(mask_surface, self.rect.topleft)"""

class Fleche(Player):

	def __init__(self, x , y , mousepos):
		pygame.sprite.Sprite.__init__(self)

		self.fleche_image = pygame.transform.scale(pygame.image.load('../graphics/fleche.png'), (64, 64))
		self.image = self.fleche_image
		self.speed_fleche = 20
		self.rect = self.fleche_image.get_rect()
		self.rect.center = (x,y)
		self.mousepos = mousepos
		self.dx, self.dy = mousepos[0] - x, mousepos[1] - y
		norm = math.sqrt(self.dx**2 + self.dy**2)
		if norm > 0:
			self.dx, self.dy = (self.dx/norm), (self.dy/norm)
    
	def update(self):
		self.rect.x += self.dx*self.speed_fleche
		self.rect.y += self.dy*self.speed_fleche

		angle = math.atan2(self.dy, self.dx)
		angle_degrees = math.degrees(angle)
		rotated_fleche = pygame.transform.rotate(self.fleche_image, -angle_degrees)
		self.image = rotated_fleche

		if self.rect.right < 0 or self.rect.left>1200:
			self.kill()