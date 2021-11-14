package com.academiasmoviles.appacademia.Adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.drawable.BitmapDrawable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;


import com.academiasmoviles.appacademia.Model.Alumno;
import com.academiasmoviles.appacademia.R;

import java.util.ArrayList;

/**
 * Created by Juan on 20/12/2016.
 */

public class AlumnoAdapter extends
        RecyclerView.Adapter<AlumnoAdapter.AlumnoAdapterViewHolder> implements View.OnClickListener
{
    private View.OnClickListener listener;
    ArrayList<Alumno> list_alumnos;
    Context context;

    public AlumnoAdapter(Context con, ArrayList<Alumno> list)
    {
        this.context = con;
        this.list_alumnos = list;
    }

    @Override
    public AlumnoAdapterViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.listitem_alumno, parent, false);

        itemView.setOnClickListener(this);

        return new AlumnoAdapterViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(AlumnoAdapterViewHolder holder, int position) {

        // Llenar la información de cada item
        Alumno alumno = list_alumnos.get(position);

        holder.tv_nombres.setText(alumno.getNombres());

        holder.tv_apellidos.setText(alumno.getApellidos());

        Bitmap bitmap = ((((BitmapDrawable) context.getResources().getDrawable(R.drawable.user_empty,null)).getBitmap()));
        bitmap = getRoundBitmap(bitmap);
        holder.imv_foto.setImageBitmap(bitmap);
    }

    @Override
    public int getItemCount() {

        return list_alumnos.size();
    }

    public void removeItem(int position)
    {
        list_alumnos.remove(position);

        notifyItemRemoved(position);
    }

    @Override
    public void onClick(View v) {

        if (listener != null)
        {
            listener.onClick(v);
        }
    }

    public void setOnClickListener(View.OnClickListener listener)
    {
        this.listener = listener;
    }

    public class AlumnoAdapterViewHolder extends RecyclerView.ViewHolder {

        TextView tv_nombres, tv_apellidos;
        ImageView imv_foto;
        ImageButton ibt_ubicacion;

        public AlumnoAdapterViewHolder(View itemView) {
            super(itemView);

            // Inicializamos los controles
            tv_nombres = (TextView) itemView.findViewById(R.id.tv_nombres);

            tv_apellidos = (TextView) itemView.findViewById(R.id.tv_apellidos);

            imv_foto = (ImageView) itemView.findViewById(R.id.img_foto);

            ibt_ubicacion = (ImageButton) itemView.findViewById(R.id.ibt_ubicacion);

            ibt_ubicacion.setOnClickListener(listener);
        }
    }

    // Redondear imagen
    public static Bitmap getRoundBitmap(Bitmap bitmap) {

        int min = Math.min(bitmap.getWidth(), bitmap.getHeight());

        Bitmap bitmapRounded = Bitmap.createBitmap(min, min, bitmap.getConfig());

        Canvas canvas = new Canvas(bitmapRounded);
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setShader(new BitmapShader(bitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP));
        canvas.drawRoundRect((new RectF(0.0f, 0.0f, min, min)), min/2, min/2, paint);

        return bitmapRounded;
    }
}
