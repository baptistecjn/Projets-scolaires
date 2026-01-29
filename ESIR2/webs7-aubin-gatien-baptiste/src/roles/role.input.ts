import { ApiProperty } from "@nestjs/swagger";

export class RoleInput {

    @ApiProperty()
    name: string;

    @ApiProperty()
    idUser: number;

    @ApiProperty()
    idAssociation: number;
}
