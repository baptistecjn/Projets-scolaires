import math


class Hook:
    def __init__(self):
        super().__init__()
        self.is_being_thrown = False
        self.throw_speed = 0.2  # 5 frames to extend entirely
        self.has_attached = False

        self.current_length = 0.0  # 0 < self.current_length < 1
        self.max_length = 1.01  # greater than 1 so the hook can fully extend
        self.min_length = 0.1

        self.projected_end_x = 0
        self.projected_end_y = 0

        self.last_throw = 0
        self.cooldown = 1

    def throw(self, angle, x, y, timer):
        self.is_being_thrown = True
        self.projected_end_x = x + self.max_length * math.cos(angle)
        self.projected_end_y = y + self.max_length * math.sin(angle)
        self.last_throw = timer

    def retract(self):
        self.has_attached = False
        self.is_being_thrown = False

    def attach(self):
        self.has_attached = True
        self.is_being_thrown = False

    def add_length(self, dx):
        self.current_length = max(0.0, min(self.max_length, self.current_length + dx))

    def update(self):
        if self.is_being_thrown:
            self.add_length(self.throw_speed)
            if self.current_length == self.max_length:
                self.retract()
        else:
            if not self.has_attached:
                self.add_length(-2 * self.throw_speed)
