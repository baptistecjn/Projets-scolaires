import pygame
import math
class Hook:
    def __init__(self, max_length):
        self.current_length = 0
        self.is_being_thrown = False
        self.is_at_max_length = False
        self.has_attached = False
        self.max_length = max_length
        self.gun_angle = 0
        self.speed = 100
        self.start = pygame.math.Vector2(0,0)
        self.end = pygame.math.Vector2(0,0)
        self.collision_point = pygame.math.Vector2(0,0)
        self.collided = False

    def set_collision_point(self, point):
        if self.collision_point != pygame.math.Vector2(0,0):
            pass
        else:
            self.collision_point = point

    def set_gun_angle(self, angle):
        self.gun_angle = angle
    
    def throw(self,window, pos):
        if self.is_being_thrown and not self.has_attached:
            start_pos = pos  # player position
            end_pos = (
                start_pos[0] + self.current_length * math.cos(math.radians(self.gun_angle)),
                start_pos[1] + self.current_length * math.sin(math.radians(self.gun_angle)),
            )
            self.start = start_pos
            self.end = end_pos
            pygame.draw.line(window, (255, 0, 0), start_pos, end_pos)

    def retract(self):
        self.is_being_thrown = False
        self.current_length = 0
        self.is_at_max_length = False
        self.has_attached = False
        self.collision_point = pygame.math.Vector2(0,0)

    def update(self,window,pos):
        if self.is_being_thrown:
            self.throw(window,pos)
            if not self.is_at_max_length:
                self.current_length += self.speed
            if self.current_length >= self.max_length:
                self.current_length = self.max_length
                self.is_at_max_length = True