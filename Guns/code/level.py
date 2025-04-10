import pygame
import time
import math
from support import import_csv_layout, import_cut_graphics, line_line_collision, line_rect_collision_point
from settings import tile_size, screen_height, screen_width
from tiles import Tile, StaticTile, Crate, Coin, Palm
from player import Player
from particles import ParticleEffect
from hook import Hook


class Level:
	def __init__(self,level_data,surface):
		# general setup
		self.display_surface = surface
		self.world_shift = 0
		self.current_x = None

		# player
		player_layout = import_csv_layout(level_data['player'])
		self.player = pygame.sprite.GroupSingle()
		self.goal = pygame.sprite.GroupSingle()
		self.player_setup(player_layout)

		# dust
		self.dust_sprite = pygame.sprite.GroupSingle()
		self.player_on_ground = False

		# terrain setup
		terrain_layout = import_csv_layout(level_data['terrain'])
		self.terrain_sprites = self.create_tile_group(terrain_layout,'terrain')

	def create_tile_group(self,layout,type):
		sprite_group = pygame.sprite.Group()

		for row_index, row in enumerate(layout):
			for col_index,val in enumerate(row):
				if val != '-1':
					x = col_index * tile_size
					y = row_index * tile_size

					if type == 'terrain':
						terrain_tile_list = import_cut_graphics('../graphics/terrain/terrain_tiles.png')
						tile_surface = terrain_tile_list[int(val)]
						sprite = StaticTile(tile_size,x,y,tile_surface)

					sprite_group.add(sprite)
		
		return sprite_group

	def player_setup(self,layout):
		for row_index, row in enumerate(layout):
			for col_index,val in enumerate(row):
				x = col_index * tile_size
				y = row_index * tile_size
				if val == '0':
					sprite = Player((x,y))
					self.player.add(sprite)

	def create_jump_particles(self,pos):
		if self.player.sprite.facing_right:
			pos -= pygame.math.Vector2(10,5)
		else:
			pos += pygame.math.Vector2(10,-5)
		jump_particle_sprite = ParticleEffect(pos,'jump')
		self.dust_sprite.add(jump_particle_sprite)

	def horizontal_movement_collision(self):
		player = self.player.sprite
		player.rect.x += player.direction.x * player.speed

		collided_sprites = pygame.sprite.spritecollide(player, self.terrain_sprites.sprites(), False)
		for sprite in collided_sprites:
			if player.direction.x < 0: 
				player.rect.left = sprite.rect.right
				player.on_left = True
				self.current_x = player.rect.left
			elif player.direction.x > 0:
				player.rect.right = sprite.rect.left
				player.on_right = True
				self.current_x = player.rect.right

		if player.on_left and (player.rect.left < self.current_x or player.direction.x >= 0):
			player.on_left = False
		if player.on_right and (player.rect.right > self.current_x or player.direction.x <= 0):
			player.on_right = False

	def vertical_movement_collision(self):
		player = self.player.sprite
		player.apply_gravity()
		collided_sprites = pygame.sprite.spritecollide(player, self.terrain_sprites.sprites(), False)
		
		for sprite in collided_sprites:
			if player.direction.y > 0: 
				player.rect.bottom = sprite.rect.top
				player.direction.y = 0
				player.on_ground = True
				player.jump_count = 0
			elif player.direction.y < 0:
				player.rect.top = sprite.rect.bottom
				player.direction.y = 0
				player.on_ceiling = True

		if player.on_ground and player.direction.y < 0 or player.direction.y > 1:
			player.on_ground = False
		if player.on_ceiling and player.direction.y > 0.1:
			player.on_ceiling = False

	def check_hook_collision(self):
		hook = self.player.sprite.hook

		# Check if the hook is currently being thrown
		if hook.is_being_thrown:
			line_start = hook.start
			line_end = hook.end

			for sprite in self.terrain_sprites.sprites():
				collision_point = line_rect_collision_point(line_start, line_end, sprite.rect)
				if collision_point:
					distance = math.sqrt((collision_point[0] - hook.start[0])**2 + (collision_point[1] - hook.start[1])**2)
					hook.end = collision_point
					hook.collided = True
					hook.current_length = distance
					hook.set_collision_point(collision_point)
					hook.has_attached = True
					print(hook.collision_point)
					break

	def scroll_x(self):
		player = self.player.sprite
		player_x = player.rect.centerx
		direction_x = player.direction.x

		if player_x < screen_width / 4 and direction_x < 0:
			self.world_shift = 8
			player.speed = 0
		elif player_x > screen_width - (screen_width / 4) and direction_x > 0:
			self.world_shift = -8
			player.speed = 0
		else:
			self.world_shift = 0
			player.speed = 8

	def get_player_on_ground(self):
		if self.player.sprite.on_ground:
			self.player_on_ground = True
			self.player.sprite.jump_count = 0
		else:
			self.player_on_ground = False

	def create_landing_dust(self):
		if not self.player_on_ground and self.player.sprite.on_ground and not self.dust_sprite.sprites():
			if self.player.sprite.facing_right:
				offset = pygame.math.Vector2(0,20)
			else:
				offset = pygame.math.Vector2(0,20)
			fall_dust_particle = ParticleEffect(self.player.sprite.rect.midbottom - offset,'land')
			self.dust_sprite.add(fall_dust_particle)

	def run(self):
		# run the entire game

		# terrain
		self.terrain_sprites.update(self.world_shift)
		self.terrain_sprites.draw(self.display_surface)

		# dust particles 
		self.dust_sprite.update(self.world_shift)
		self.dust_sprite.draw(self.display_surface)

		# player sprites
		self.horizontal_movement_collision()
		
		self.get_player_on_ground()
		self.vertical_movement_collision()
		self.create_landing_dust()
		
		self.scroll_x()
		self.check_hook_collision()
		self.player.update(self.display_surface)
		self.player.draw(self.display_surface)
