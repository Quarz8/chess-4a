/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Jacob Freeland
 */
public class Queen extends Piece
{
    public Queen()
    {
        this.white = true;
        this.charRep = 'Q';
    }
    
    public Queen(boolean white)
    {
        this.white = white;
        this.charRep = (white ? 'Q' : 'q');
    }
}