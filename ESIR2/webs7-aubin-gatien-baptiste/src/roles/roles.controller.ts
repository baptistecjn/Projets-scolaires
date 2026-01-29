import { Controller, Get, Post, Put, Delete, Param, Body } from '@nestjs/common';
import { RolesService } from './roles.service';
import { RoleInput } from './role.input';
import { RoleUpdate } from './role.update';

@Controller('roles')
export class RolesController {
    constructor(private rolesService: RolesService) {}

    @Get()
    getAll() {
        return this.rolesService.findAll();
    }

    @Get(':idUser/:idAssociation')
    getOne(
        @Param('idUser') idUser: number,
        @Param('idAssociation') idAssociation: number
    ) {
        return this.rolesService.findOne(idUser, idAssociation);
    }

    @Post()
    create(@Body() input: RoleInput) {
        return this.rolesService.create(input);
    }

    @Put(':idUser/:idAssociation')
    update(
        @Param('idUser') idUser: number,
        @Param('idAssociation') idAssociation: number,
        @Body() update: RoleUpdate
    ) {
        return this.rolesService.update(idUser, idAssociation, update);
    }

    @Delete(':idUser/:idAssociation')
    delete(
        @Param('idUser') idUser: number,
        @Param('idAssociation') idAssociation: number
    ) {
        return this.rolesService.delete(idUser, idAssociation);
    }

    @Get('users/:name')
        async getUsersByRole(@Param('name') name: string) {
        return this.rolesService.findUsersByRoleName(name);
}

}
