import { Picture } from "./picture";

export class Profile {

    constructor(
        private _userName: string,
        private _password: string,
        private _profilePicture: string,
        private _visibility: string,
        private _buddyNum: number,
        private _pics: Picture[]
    ){}

    public get getvisibility(): string {
        return this._visibility;
    }
    public set setvisibility(value: string) {
        this._visibility = value;
    }
    public get getprofilePicture(): string {
        return this._profilePicture;
    }
    public set setprofilePicture(value: string) {
        this._profilePicture = value;
    }
    public get getpassword(): string {
        return this._password;
    }
    public set setpassword(value: string) {
        this._password = value;
    }
    public get getuserName(): string {
        return this._userName;
    }
    public set setuserName(value: string) {
        this._userName = value;
    }

    public get getpics(): Picture[] {
        return this._pics;
    }
    public set setpics(value: Picture[]) {
        this._pics = value;
    }

    public get getbuddyNum(): number {
        return this._buddyNum;
    }
    public set setbuddyNum(value: number) {
        this._buddyNum = value;
    }
}
