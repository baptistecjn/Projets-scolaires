import { Test, TestingModule } from '@nestjs/testing';
import { getRepositoryToken } from '@nestjs/typeorm';
import { Repository } from 'typeorm';
import { User } from './user.entity';
import { UsersController } from './users.controller';
import { UsersService } from './users.service';
import passport from 'passport';

export type MockType<T> = {
  [P in keyof T]?: jest.Mock<{}>;
};

export const repositoryMockFactory: () => MockType<Repository<any>> = jest.fn(() => ({
  findOne: jest.fn(entity => entity),
}));

describe('UsersController', () => {
  let controller: UsersController;
  let service: UsersService;

  beforeEach(async () => {
    const module: TestingModule = await Test.createTestingModule({
      controllers: [UsersController],
      providers: [
        UsersService,
        { provide: getRepositoryToken(User), useFactory: repositoryMockFactory }
      ]
    }).compile();

    service = module.get<UsersService>(UsersService);
    controller = module.get<UsersController>(UsersController);
  });

  describe('getAll', () => {
    it('should return an array of users', async () => {
      const expected = Promise.all([{
        id: 0,
        firstname: 'John',
        lastname: 'Doe',
        age: 18,
        password: 'Password1'
      }]);
      jest.spyOn(service, 'getAll').mockImplementation(() => expected);
      expect(await controller.getAll()).toBe(await expected);
    });
  });
  describe('getById', () => {
    it('should return a single user with the provided id', async () => {
      const expected = {
        id: 0,
        firstname: 'John',
        lastname: 'Doe',
        age: 18,
        password: 'Password1'
      };

      jest.spyOn(service, 'getById').mockImplementation(async (id: number) => expected);

      expect(await controller.getById("0")).toBe(expected);
    });
  });

  describe('delById', () => {
    it('should delete user and return true', async () => {
      jest.spyOn(service, 'delById').mockImplementation(async (id: number) => true);

      expect(await controller.delById("0")).toBe(true);
    });
  });

  describe('update', () => {
    it('should update and return the updated user', async () => {
      const updatedUser = {
        id: 0,
        firstname: 'John',
        lastname: 'DoeUpdated',
        age: 20,
        password: 'NewPass'
      };

      jest.spyOn(service, 'update').mockImplementation(
        async (id: number, lastname?: string, firstname?: string, age?: number, password?: string) =>
          updatedUser
      );

      expect(await controller.update("0", { lastname: 'DoeUpdated', firstname: 'John', age: 20, password: 'NewPass' })).toBe(updatedUser);
    });
  });


});