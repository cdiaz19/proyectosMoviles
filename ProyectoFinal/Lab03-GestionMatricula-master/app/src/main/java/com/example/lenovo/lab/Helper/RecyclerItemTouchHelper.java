package com.example.lenovo.lab.Helper;

import android.animation.ArgbEvaluator;
import android.animation.ValueAnimator;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.View;

import com.example.lenovo.lab.Adapter.ClientAdapter;
import com.example.lenovo.lab.Adapter.CategoriesAdapter;
import com.example.lenovo.lab.Adapter.VideoGameAdapter;
import com.example.lenovo.lab.Adapter.SecurityAdapter;

/**
 * Created by Luis Carrillo Rodriguez on 18/3/2018.
 */

public class RecyclerItemTouchHelper extends ItemTouchHelper.SimpleCallback {

  private RecyclerItemTouchHelperListener listener;
  private View foregroundView;
  private View backgroundViewEdit;
  private View backgroundViewDelete;
  private int dragColor = Color.rgb(102, 102, 255);

  public RecyclerItemTouchHelper(int dragDirs, int swipeDirs, RecyclerItemTouchHelperListener listener) {
    super(dragDirs, swipeDirs);
    this.listener = listener;
  }

  @Override
  public boolean isLongPressDragEnabled() {
    return true;
  }

  @Override
  public boolean isItemViewSwipeEnabled() {
    return true;
  }

  @Override
  public int getMovementFlags(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
    int dragFlags = ItemTouchHelper.UP | ItemTouchHelper.DOWN;
    int swipeFlags = ItemTouchHelper.START | ItemTouchHelper.END;
    return makeMovementFlags(dragFlags, swipeFlags);
  }

  @Override
  public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder,
                        RecyclerView.ViewHolder target) {
    listener.onItemMove(viewHolder.getAdapterPosition(), target.getAdapterPosition());
    return true;
  }

  @Override
  public void onSelectedChanged(RecyclerView.ViewHolder viewHolder, int actionState) {
    if (viewHolder != null) {
      //check adapter
      if (this.listener.getClass().getSimpleName().equals("AdmCategoryActivity")) {
        backgroundViewEdit = ((CategoriesAdapter.MyViewHolder) viewHolder).viewBackgroundEdit;
        backgroundViewDelete = ((CategoriesAdapter.MyViewHolder) viewHolder).viewBackgroundDelete;
        foregroundView = ((CategoriesAdapter.MyViewHolder) viewHolder).viewForeground;

      } else if (this.listener.getClass().getSimpleName().equals("AdmAlumnoActivity")) {
        backgroundViewEdit = ((ClientAdapter.MyViewHolder) viewHolder).viewBackgroundEdit;
        backgroundViewDelete = ((ClientAdapter.MyViewHolder) viewHolder).viewBackgroundDelete;
        foregroundView = ((ClientAdapter.MyViewHolder) viewHolder).viewForeground;

      } else if (this.listener.getClass().getSimpleName().equals("AdmGrupoActivity")) {
        backgroundViewEdit = ((VideoGameAdapter.MyViewHolder) viewHolder).viewBackgroundEdit;
        backgroundViewDelete = ((VideoGameAdapter.MyViewHolder) viewHolder).viewBackgroundDelete;
        foregroundView = ((VideoGameAdapter.MyViewHolder) viewHolder).viewForeground;

      } else if (this.listener.getClass().getSimpleName().equals("AdmSecurityActivity")) {
        backgroundViewEdit = ((SecurityAdapter.MyViewHolder) viewHolder).viewBackgroundEdit;
        backgroundViewDelete = ((SecurityAdapter.MyViewHolder) viewHolder).viewBackgroundDelete;
        foregroundView = ((SecurityAdapter.MyViewHolder) viewHolder).viewForeground;

      }

      if (actionState == ItemTouchHelper.ACTION_STATE_DRAG) {
        //fancy color picked
        ValueAnimator colorAnimation = ValueAnimator.ofObject(new ArgbEvaluator(), Color.WHITE, dragColor);
        colorAnimation.setDuration(250); // milliseconds
        colorAnimation.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
          @Override
          public void onAnimationUpdate(ValueAnimator animator) {
            foregroundView.setBackgroundColor((int) animator.getAnimatedValue());
          }
        });
        colorAnimation.start();
      }
      getDefaultUIUtil().onSelected(foregroundView);
      super.onSelectedChanged(viewHolder, actionState);
    }
  }

  @Override
  public void onChildDrawOver(Canvas c, RecyclerView recyclerView,
                              RecyclerView.ViewHolder viewHolder, float dX, float dY,
                              int actionState, boolean isCurrentlyActive) {
    getDefaultUIUtil().onDrawOver(c, recyclerView, foregroundView, dX, dY,
      actionState, isCurrentlyActive);
  }

  @Override
  public void clearView(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
    //clear view with fancy animation
    int color = Color.TRANSPARENT;
    Drawable background = foregroundView.getBackground();
    if (background instanceof ColorDrawable)
      color = ((ColorDrawable) background).getColor();
    //check color
    if (color == dragColor) {
      ValueAnimator colorAnimation = ValueAnimator.ofObject(new ArgbEvaluator(), dragColor, Color.WHITE);
      colorAnimation.setDuration(250); // milliseconds
      colorAnimation.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
        @Override
        public void onAnimationUpdate(ValueAnimator animator) {
          foregroundView.setBackgroundColor((int) animator.getAnimatedValue());
        }
      });
      colorAnimation.start();
    }
    super.clearView(recyclerView, viewHolder);
    getDefaultUIUtil().clearView(foregroundView);
  }

  @Override
  public void onChildDraw(Canvas c, RecyclerView recyclerView,
                          RecyclerView.ViewHolder viewHolder, float dX, float dY,
                          int actionState, boolean isCurrentlyActive) {
    if (actionState == ItemTouchHelper.ACTION_STATE_SWIPE) {
      drawBackground(dX);
      getDefaultUIUtil().onDraw(c, recyclerView, foregroundView, dX, dY,
        actionState, isCurrentlyActive);
    }
  }

  private void drawBackground(float dX) {
    if (this.listener.getClass().getSimpleName().equals("MatriculaActivity")) {
      backgroundViewDelete.setVisibility(View.VISIBLE);
    } else {
      if (dX > 0) {
        backgroundViewEdit.setVisibility(View.VISIBLE);
        backgroundViewDelete.setVisibility(View.GONE);
      } else {
        backgroundViewDelete.setVisibility(View.VISIBLE);
        backgroundViewEdit.setVisibility(View.GONE);
      }
    }
  }

  @Override
  public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
    listener.onSwiped(viewHolder, direction, viewHolder.getAdapterPosition());
  }

  @Override
  public int convertToAbsoluteDirection(int flags, int layoutDirection) {
    return super.convertToAbsoluteDirection(flags, layoutDirection);
  }

  public interface RecyclerItemTouchHelperListener {
    void onSwiped(RecyclerView.ViewHolder viewHolder, int direction, int position);

    void onItemMove(int source, int target);
  }
}
